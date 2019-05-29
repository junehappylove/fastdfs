/**
 * 
 */
package com.june.fastdfs.exception;

/**
 * 非fastdfs本身的错误码抛出的异常，socket连不上时抛出的异常<br>
 * 
 * @author junehappylove
 *
 */
public class FdfsConnectException extends FdfsUnavailableException {

	private static final long serialVersionUID = 19880613L;

	public FdfsConnectException(String message, Throwable t) {
		super(message, t);
	}
}
