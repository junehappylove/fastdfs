/**
 * 
 */
package com.june.fastdfs.socket;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author junehappylove
 *
 */
public class FdfsSocketPool extends GenericObjectPool<PooledFdfsSocket> {

	public FdfsSocketPool(FdfsSocketFactory factory, GenericObjectPoolConfig config) {
		super(factory, config);
		factory.setPool(this);
	}
}
