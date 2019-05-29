/**
 * 
 */
package com.june.fastdfs.socket;

import java.net.InetSocketAddress;

import com.june.fastdfs.exception.FdfsConnectException;

import lombok.Setter;

/**
 * @author junehappylove
 *
 */
public class BorrowSockectErrorCreateNotPooledPolicy implements IBorrowSockectErrorPolicy {

	@Setter
	private int soTimeout;
	@Setter
	private int connectTimeout;

	public void init() {
		if (connectTimeout <= 0) {
			connectTimeout = FdfsSocketService.DEFAULT_CONNECT_TIMEOUT;
		}

		if (soTimeout <= 0) {
			soTimeout = FdfsSocketService.DEFAULT_NETWORK_TIMEOUT;
		}

	}

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

		return FdfsSocket.create(address, soTimeout, connectTimeout);
	}

}
