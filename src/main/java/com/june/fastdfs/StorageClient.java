/**
 * 
 */
package com.june.fastdfs;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 向tracker请求上传、下载文件或其他文件请求时，tracker返回的文件storage节点的信息<br>
 * 
 * @author junehappylove
 *
 */
@AllArgsConstructor
public class StorageClient {

	@Getter
	private final InetSocketAddress inetSocketAddress;
	@Getter
	private final Charset charset;
	@Getter
	private final byte storeIndex;

}
