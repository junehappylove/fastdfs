/**
 * 
 */
package com.june.fastdfs.exception;

/**
 * 非fastdfs本身的错误码抛出的异常，取服务端连接取不到时抛出的异常<br>
 * 
 * @author junehappylove
 *
 */
public class FdfsUnavailableException extends FdfsException {

	private static final long serialVersionUID = 19880613L;

	public FdfsUnavailableException(String message) {
		super("无法获取服务端连接资源：" + message);
	}

	public FdfsUnavailableException(String message, Throwable t) {
		super("无法获取服务端连接资源：" + message, t);
	}
}
