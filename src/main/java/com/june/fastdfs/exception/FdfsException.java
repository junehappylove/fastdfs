/**
 * 
 */
package com.june.fastdfs.exception;

/**
 * 封装fastdfs的异常，使用非受检异常<br>
 * 
 * @author junehappylove
 *
 */
public class FdfsException extends RuntimeException {

	private static final long serialVersionUID = 19880613L;

	protected FdfsException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 *            消息
	 * @param cause
	 *            原因
	 */
	protected FdfsException(String message, Throwable cause) {
		super(message, cause);
	}

}
