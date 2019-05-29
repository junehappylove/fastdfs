/**
 * 
 */
package com.june.fastdfs.socket;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.june.fastdfs.exception.FdfsConnectException;

import lombok.Setter;

/**
 * @author junehappylove
 *
 */
public class FdfsSocketFactory extends BasePooledObjectFactory<PooledFdfsSocket> {

	@Setter
	private FdfsSocketPool pool;

	private InetSocketAddress socketAddress;
	private int soTimeout;
	private int connectTimeout;

	public FdfsSocketFactory(InetSocketAddress socketAddress, int soTimeout, int connectTimeout) {
		super();
		// this.pool = pool;
		this.socketAddress = socketAddress;
		this.soTimeout = soTimeout;
		this.connectTimeout = connectTimeout;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.pool2.BasePooledObjectFactory#create()
	 */
	@Override
	public PooledFdfsSocket create() throws Exception {
		try {
			PooledFdfsSocket socket = new PooledFdfsSocket(pool);
			socket.setSoTimeout(soTimeout);
			socket.connect(socketAddress, connectTimeout);
			return socket;
		} catch (IOException e) {
			throw new FdfsConnectException("can't create socket", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.pool2.BasePooledObjectFactory#wrap(java.lang.Object)
	 */
	@Override
	public PooledObject<PooledFdfsSocket> wrap(PooledFdfsSocket obj) {
		return new DefaultPooledObject<PooledFdfsSocket>(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.pool2.BasePooledObjectFactory#destroyObject(org.apache.commons.pool2.PooledObject)
	 */
	@Override
	public void destroyObject(PooledObject<PooledFdfsSocket> socket) throws Exception {
		socket.getObject().destroy();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.pool2.BasePooledObjectFactory#validateObject(org.apache.commons.pool2.PooledObject)
	 */
	@Override
	public boolean validateObject(PooledObject<PooledFdfsSocket> socket) {
		if (socket.getObject().isNeedDestroy()) {
			return false;
		}
		return socket.getObject().check();
	}
}
