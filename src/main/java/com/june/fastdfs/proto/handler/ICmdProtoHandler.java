/**
 * 
 */
package com.june.fastdfs.proto.handler;

/**
 * @author junehappylove
 *
 */
public interface ICmdProtoHandler<T> {

	/**
	 * 处理
	 * 
	 * @return 返回处理的对象
	 */
	T handle();
}
