/**
 * 
 */
package com.june.fastdfs.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author junehappylove
 *
 * @param <T>
 *            处理对象
 */
public interface IFdfsFileInputStreamHandler<T> {

	/**
	 * 处理完毕后根据ins的实际情况进行关闭socket操作，注意不能直接返回入参的InputStream，因为此方法返回后将关闭原输入流
	 * 
	 * @param ins
	 *            输入流
	 * @throws IOException
	 *             io异常
	 * @return 处理对象
	 * @param <T>
	 *            处理对象
	 */
	T deal(InputStream ins) throws IOException;
}
