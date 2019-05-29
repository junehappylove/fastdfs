/**
 * 
 */
package com.june.fastdfs.socket;

import java.io.IOException;

import org.apache.commons.pool2.impl.GenericObjectPool;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author junehappylove
 *
 */
@Slf4j
public class PooledFdfsSocket extends FdfsSocket {

	private GenericObjectPool<PooledFdfsSocket> pool;
	@Setter
	@Getter
	private boolean needDestroy;

	/**
	 * @param pool
	 */
	public PooledFdfsSocket(GenericObjectPool<PooledFdfsSocket> pool) {
		super();
		this.pool = pool;
	}

	/**
	 * 当客户端关闭连接的时候状态设置为true(空闲）
	 */
	@Override
	public synchronized void close() throws IOException {
		if (needDestroy) {
			try {
				pool.invalidateObject(this);
			} catch (Exception ignore) {
				log.warn("error occurs when invalidate socket in pool", ignore);
			}
		} else {
			try {
				pool.returnObject(this);
			} catch (Exception ignore) {
				log.warn("error occurs when return socket to pool", ignore);
			}
		}
	}

	protected synchronized void destroy() throws IOException {
		super.close();
	}
}
