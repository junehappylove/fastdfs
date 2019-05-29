/**
 * 
 */
package com.june.fastdfs.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.june.fastdfs.service.IFdfsFileInputStreamHandler;

/**
 * @author junehappylove
 *
 */
public class ByteArrayFdfsFileInputStreamHandler implements IFdfsFileInputStreamHandler<byte[]> {

	@Override
	public byte[] deal(InputStream ins) throws IOException {
		return IOUtils.toByteArray(ins);
	}

}
