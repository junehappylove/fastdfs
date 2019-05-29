/**
 * 
 */
package com.june.fastdfs.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.june.fastdfs.FileInfo;
import com.june.fastdfs.NameValuePair;
import com.june.fastdfs.StorageClient;
import com.june.fastdfs.StorePath;
import com.june.fastdfs.exception.FdfsIOException;
import com.june.fastdfs.proto.OtherConstants;
import com.june.fastdfs.proto.handler.ICmdProtoHandler;
import com.june.fastdfs.proto.handler.StorageAppendHandler;
import com.june.fastdfs.proto.handler.StorageDeleteHandler;
import com.june.fastdfs.proto.handler.StorageDownloadHandler;
import com.june.fastdfs.proto.handler.StorageGetMetadataHandler;
import com.june.fastdfs.proto.handler.StorageModifyHandler;
import com.june.fastdfs.proto.handler.StorageQueryFileInfoHandler;
import com.june.fastdfs.proto.handler.StorageSetMetadataHandler;
import com.june.fastdfs.proto.handler.StorageTruncateHandler;
import com.june.fastdfs.proto.handler.StorageUploadHandler;
import com.june.fastdfs.proto.handler.StorageUploadSlaveHandler;
import com.june.fastdfs.service.IFdfsFileInputStreamHandler;
import com.june.fastdfs.service.IStorageClientService;
import com.june.fastdfs.service.ITrackerClientService;
import com.june.fastdfs.socket.FdfsInputStream;
import com.june.fastdfs.socket.FdfsSocket;
import com.june.fastdfs.socket.FdfsSocketService;
import com.june.fastdfs.socket.PooledFdfsSocket;

import lombok.Setter;

/**
 * @author junehappylove
 *
 */
public class StorageClientService implements IStorageClientService {

	@Setter
	private FdfsSocketService fdfsSocketService;
	@Setter
	private ITrackerClientService trackerClientService;

	private <T> T process(FdfsSocket socket, ICmdProtoHandler<T> handler) {
		try {
			return handler.handle();
		} finally {
			IOUtils.closeQuietly(socket);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.june.fastdfs.service.IStorageClientService#uploadFile(java.lang.
	 * String, java.io.InputStream, long, java.lang.String)
	 */
	@Override
	public StorePath uploadFile(String groupName, InputStream ins, long size, String ext) {
		StorageClient storageClient = trackerClientService.getStoreStorage(groupName);

		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<StorePath> handler = new StorageUploadHandler(socket, false, ins, size,
				storageClient.getStoreIndex(), ext, storageClient.getCharset());
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#uploadAppenderFile(java.
	 * lang.String, java.io.InputStream, long, java.lang.String)
	 */
	@Override
	public StorePath uploadAppenderFile(String groupName, InputStream ins, long size, String ext) {
		StorageClient storageClient = trackerClientService.getStoreStorage(groupName);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<StorePath> handler = new StorageUploadHandler(socket, true, ins, size,
				storageClient.getStoreIndex(), ext, storageClient.getCharset());
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#uploadSlaveFile(java.lang.
	 * String, java.lang.String, java.io.InputStream, long, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public StorePath uploadSlaveFile(String groupName, String masterFilename, InputStream ins, long size,
			String prefixName, String ext) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, masterFilename);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<StorePath> handler = new StorageUploadSlaveHandler(socket, ins, size, masterFilename,
				prefixName, ext, storageClient.getCharset());
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.june.fastdfs.service.IStorageClientService#appendFile(java.lang.
	 * String, java.lang.String, java.io.InputStream, long)
	 */
	@Override
	public void appendFile(String groupName, String path, InputStream ins, long size) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<Void> handler = new StorageAppendHandler(socket, ins, size, path, storageClient.getCharset());
		process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.june.fastdfs.service.IStorageClientService#modifyFile(java.lang.
	 * String, java.lang.String, long, java.io.InputStream, long)
	 */
	@Override
	public void modifyFile(String groupName, String path, long offset, InputStream ins, long size) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<Void> handler = new StorageModifyHandler(socket, ins, size, path, offset,
				storageClient.getCharset());
		process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.june.fastdfs.service.IStorageClientService#deleteFile(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void deleteFile(String groupName, String path) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);

		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<Void> handler = new StorageDeleteHandler(socket, groupName, path, storageClient.getCharset());
		process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#truncateFile(java.lang.
	 * String, java.lang.String, long)
	 */
	@Override
	public void truncateFile(String groupName, String path, long truncatedFileSize) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);

		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<Void> handler = new StorageTruncateHandler(socket, path, truncatedFileSize,
				storageClient.getCharset());
		process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#downloadFile(java.lang.
	 * String, java.lang.String,
	 * com.june.fastdfs.service.IFdfsFileInputStreamHandler)
	 */
	@Override
	public <T> T downloadFile(String groupName, String path, IFdfsFileInputStreamHandler<T> handling) {
		long offset = 0;
		long size = 0;
		return downloadFile(groupName, path, offset, size, handling);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#downloadFile(java.lang.
	 * String, java.lang.String, long, long,
	 * com.june.fastdfs.service.IFdfsFileInputStreamHandler)
	 */
	@Override
	public <T> T downloadFile(String groupName, String path, long offset, long size,
			IFdfsFileInputStreamHandler<T> handling) {
		StorageClient storageClient = trackerClientService.getFetchStorage(groupName, path);

		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<FdfsInputStream> handler = new StorageDownloadHandler(socket, groupName, path, offset, size,
				storageClient.getCharset());

		try {
			FdfsInputStream fdfsInputStream = handler.handle();
			T result = handling.deal(fdfsInputStream);

			if (!fdfsInputStream.isReadCompleted() && socket instanceof PooledFdfsSocket) {
				((PooledFdfsSocket) socket).setNeedDestroy(true);
			}
			IOUtils.closeQuietly(fdfsInputStream);
			return result;
		} catch (IOException e) {
			if (socket instanceof PooledFdfsSocket) {
				((PooledFdfsSocket) socket).setNeedDestroy(true);
			}
			throw new FdfsIOException(e);
		} finally {
			IOUtils.closeQuietly(socket);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#getMetadata(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public NameValuePair[] getMetadata(String groupName, String path) {
		StorageClient storageClient = trackerClientService.getFetchStorage(groupName, path);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<NameValuePair[]> handler = new StorageGetMetadataHandler(socket, groupName, path,
				storageClient.getCharset());
		return process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#overwriteMetadata(java.
	 * lang.String, java.lang.String, com.june.fastdfs.NameValuePair[])
	 */
	@Override
	public void overwriteMetadata(String groupName, String path, NameValuePair[] metaList) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<Void> handler = new StorageSetMetadataHandler(socket, groupName, path, metaList,
				OtherConstants.STORAGE_SET_METADATA_FLAG_OVERWRITE, storageClient.getCharset());
		process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#mergeMetadata(java.lang.
	 * String, java.lang.String, com.june.fastdfs.NameValuePair[])
	 */
	@Override
	public void mergeMetadata(String groupName, String path, NameValuePair[] metaList) {
		StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<Void> handler = new StorageSetMetadataHandler(socket, groupName, path, metaList,
				OtherConstants.STORAGE_SET_METADATA_FLAG_MERGE, storageClient.getCharset());
		process(socket, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.service.IStorageClientService#queryFileInfo(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public FileInfo queryFileInfo(String groupName, String path) {
		StorageClient storageClient = trackerClientService.getFetchStorage(groupName, path);
		FdfsSocket socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
		ICmdProtoHandler<FileInfo> handler = new StorageQueryFileInfoHandler(socket, groupName, path,
				storageClient.getCharset());
		return process(socket, handler);
	}

}
