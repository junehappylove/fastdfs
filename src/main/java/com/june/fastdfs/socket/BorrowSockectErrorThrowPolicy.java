/**
 * 
 */
package com.june.fastdfs.socket;

import java.net.InetSocketAddress;

import com.june.fastdfs.exception.FdfsConnectException;
import com.june.fastdfs.exception.FdfsUnavailableException;

/**
 * @author junehappylove
 *
 */
public class BorrowSockectErrorThrowPolicy implements IBorrowSockectErrorPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.june.fastdfs.socket.IBorrowSockectErrorPolicy#handleWhenErrorOccur(
	 * com.june.fastdfs.socket.FdfsSocketPool, java.net.InetSocketAddress,
	 * java.lang.Exception)
	 */
	@Override
	public FdfsSocket handleWhenErrorOccur(FdfsSocketPool pool, InetSocketAddress address, Exception ex) {
		Throwable e = ex;
		int i = 0;
		while (e != null && i < 5) {
			if (e instanceof FdfsConnectException) {
				throw (FdfsConnectException) e;
			}
			e = e.getCause();
			i++;
		}
		throw new FdfsUnavailableException("连接池中无可用资源", ex);
	}

}
