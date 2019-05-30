/**
 * 
 */
package com.june.fastdfs.service.impl;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.june.fastdfs.GroupState;
import com.june.fastdfs.StorageClient;
import com.june.fastdfs.StorageState;
import com.june.fastdfs.exception.FdfsConnectException;
import com.june.fastdfs.exception.FdfsUnavailableException;
import com.june.fastdfs.proto.handler.ICmdProtoHandler;
import com.june.fastdfs.proto.handler.TrackerDeleteStorageHandler;
import com.june.fastdfs.proto.handler.TrackerGetFetchStorageHandler;
import com.june.fastdfs.proto.handler.TrackerGetStoreStorageHandler;
import com.june.fastdfs.proto.handler.TrackerListGroupsHandler;
import com.june.fastdfs.proto.handler.TrackerListStoragesHandler;
import com.june.fastdfs.service.ITrackerClientService;
import com.june.fastdfs.socket.FdfsSocket;
import com.june.fastdfs.socket.FdfsSocketService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author junehappylove
 *
 */
@Slf4j
public class TrackerClientService implements ITrackerClientService {

	private static final String DEFAULT_CHARSET_NAME = "ISO8859-1";

	@Setter
	private String charsetName = DEFAULT_CHARSET_NAME;
	@Setter
	private String[] trackerServerValues;
	@Setter
	private FdfsSocketService fdfsSocketService;

	private Object lock = new Object();
	private Charset charset;
	private final CircularList<TrackerAddressHolder> trackerAddresses = new CircularList<TrackerAddressHolder>();
	private int availableCount;

	public void init() {
		charset = Charset.forName(charsetName);// TODO 测试charsetName从外部注入获取的值是否仍然是 ISO8859-1

		String[] parts;

		Set<InetSocketAddress> trackerAddressSet = new HashSet<InetSocketAddress>();
		for (String trackerServersValue : trackerServerValues) {
			if (StringUtils.isBlank(trackerServersValue)) {
				continue;
			}
			parts = StringUtils.split(trackerServersValue, ":", 2);
			if (parts.length != 2) {
				throw new IllegalArgumentException(
						"the value of item \"tracker_server\" is invalid, the correct format is host:port");
			}
			InetSocketAddress address = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
			trackerAddressSet.add(address);
		}
		availableCount = trackerAddressSet.size();
		if (availableCount == 0) {
			throw new IllegalArgumentException("item \"tracker_server\"  not found");
		}

		for (InetSocketAddress address : trackerAddressSet) {
			trackerAddresses.add(new TrackerAddressHolder(address));
		}
	}

	/**
	 * 一个ip取不到就取下一个ip的连接，直到所有的ip都取过一遍还没取到报异常
	 * 
	 * @return FdfsSocket
	 */
	private FdfsSocket getTrackerSocket() {
		InetSocketAddress trackerAddresse;
		FdfsSocket socket = null;
		TrackerAddressHolder holder;
		for (int i = 0; i < trackerAddresses.size(); i++) {
			holder = trackerAddresses.next();
			// list中不是所有的的都被标记成不可用并且当前被标记成不可用时间小于10分钟的情况下，直接跳到下一个
			if (availableCount != 0 && !holder.available
					&& (System.currentTimeMillis() - holder.lastUnavailableTime) < 10 * 60 * 1000) {
				continue;
			}

			trackerAddresse = holder.address;
			try {
				socket = fdfsSocketService.getSocket(trackerAddresse);
				holder.setState(true);
			} catch (FdfsConnectException e) {
				holder.setState(false);
				holder.lastUnavailableTime = System.currentTimeMillis();
			} catch (Exception ignore) {
				log.error("ERROR", ignore);
			}
			if (socket != null) {
				return socket;
			}
		}

		throw new FdfsUnavailableException("找不到可用的tracker");
	}

	private <T> T process(Socket socket, ICmdProtoHandler<T> handler) {
		try {
			return handler.handle();
		} finally {
			IOUtils.closeQuietly(socket);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.june.fastdfs.service.ITrackerClientService#getStoreStorage()
	 */
	@Override
	public StorageClient getStoreStorage() {
		return getStoreStorage(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.ITrackerClientService#getStoreStorage(java.lang.
	 * String)
	 */
	@Override
	public StorageClient getStoreStorage(String groupName) {
		FdfsSocket socket = getTrackerSocket();
		ICmdProtoHandler<StorageClient> handler = new TrackerGetStoreStorageHandler(socket, groupName, charset);
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.ITrackerClientService#getFetchStorage(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public StorageClient getFetchStorage(String groupName, String path) {
		FdfsSocket socket = getTrackerSocket();
		ICmdProtoHandler<StorageClient> handler = new TrackerGetFetchStorageHandler(socket, false, groupName, path,
				charset);
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.ITrackerClientService#getUpdateStorage(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public StorageClient getUpdateStorage(String groupName, String path) {
		FdfsSocket socket = getTrackerSocket();
		ICmdProtoHandler<StorageClient> handler = new TrackerGetFetchStorageHandler(socket, true, groupName, path,
				charset);
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.june.fastdfs.service.ITrackerClientService#listGroups()
	 */
	@Override
	public GroupState[] listGroups() {
		FdfsSocket socket = getTrackerSocket();
		ICmdProtoHandler<GroupState[]> handler = new TrackerListGroupsHandler(socket, charset);
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.ITrackerClientService#listStorages(java.lang.
	 * String)
	 */
	@Override
	public StorageState[] listStorages(String groupName) {
		final String storageIpAddr = null;
		return listStorages(groupName, storageIpAddr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.ITrackerClientService#listStorages(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public StorageState[] listStorages(String groupName, String storageIpAddr) {
		FdfsSocket socket = getTrackerSocket();
		ICmdProtoHandler<StorageState[]> handler = new TrackerListStoragesHandler(socket, charset, groupName,
				storageIpAddr);
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.ITrackerClientService#deleteStorage(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void deleteStorage(String groupName, String storageIpAddr) {
		FdfsSocket socket = getTrackerSocket();
		ICmdProtoHandler<Void> handler = new TrackerDeleteStorageHandler(socket, charset, groupName, storageIpAddr);
		process(socket, handler);
	}

	private class TrackerAddressHolder {

		private InetSocketAddress address;
		private boolean available;
		private long lastUnavailableTime;

		private TrackerAddressHolder(InetSocketAddress address) {
			super();
			this.address = address;
			this.available = true;
		}

		private void setState(boolean available) {
			synchronized (lock) {
				if (this.available != available) {
					this.available = available;
					if (available) {
						TrackerClientService.this.availableCount++;
					} else {
						TrackerClientService.this.availableCount--;
					}
				}
			}

		}
	}

}
