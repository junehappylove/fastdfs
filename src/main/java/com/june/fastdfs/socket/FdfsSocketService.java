/**
 * 
 */
package com.june.fastdfs.socket;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author junehappylove
 *
 */
@Slf4j
public class FdfsSocketService {

	protected static final int DEFAULT_CONNECT_TIMEOUT = 5 * 1000;
	protected static final int DEFAULT_NETWORK_TIMEOUT = 30 * 1000;

	private static final IBorrowSockectErrorPolicy Default_BorrowSockectErrorPolicy = new BorrowSockectErrorThrowPolicy();

	@Setter
	private int connectTimeout; // 单位：毫秒, 连接超时时间，应用与socket connect方法的参数
	@Setter
	private int soTimeout; // 单位：毫秒，读取超时时间,对应so_timeout
	@Setter
	private FdfsPoolConfig poolConfig;
	@Setter
	private IBorrowSockectErrorPolicy borrowSockectErrorPolicy;

	private final Map<InetSocketAddress, FdfsSocketPool> poolMapping = new ConcurrentHashMap<InetSocketAddress, FdfsSocketPool>();

	public void init() {
		if (connectTimeout <= 0) {
			connectTimeout = DEFAULT_CONNECT_TIMEOUT;
		}

		if (soTimeout <= 0) {
			soTimeout = DEFAULT_NETWORK_TIMEOUT;
		}

		if (borrowSockectErrorPolicy == null) {
			borrowSockectErrorPolicy = Default_BorrowSockectErrorPolicy;
		}
	}

	public FdfsSocket getSocket(InetSocketAddress address) {
		if (poolConfig == null) {
			return FdfsSocket.create(address, soTimeout, connectTimeout);
		}

		FdfsSocketPool pool;
		synchronized (this) {
			pool = poolMapping.get(address);
			if (pool == null) {
				FdfsSocketFactory factory = new FdfsSocketFactory(address, soTimeout, connectTimeout);
				pool = new FdfsSocketPool(factory, poolConfig);
				poolMapping.put(address, pool);
			}
		}
		try {
			return pool.borrowObject();
		} catch (Exception e) {
			return borrowSockectErrorPolicy.handleWhenErrorOccur(pool, address, e);
		}
	}

	public void destroy() {
		synchronized (this) {
			for (FdfsSocketPool pool : poolMapping.values()) {
				try {
					pool.close();
					log.debug("pool current size :" + pool.getNumActive() + "-" + pool.getNumIdle());
				} catch (Exception e) {
					log.warn("destory pool error", e);
				}
			}
			poolMapping.clear();
		}
	}
}
