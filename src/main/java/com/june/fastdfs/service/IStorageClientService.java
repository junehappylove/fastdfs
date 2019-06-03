/**
 * 
 */
package com.june.fastdfs.service;

import java.io.InputStream;

import com.june.fastdfs.FileInfo;
import com.june.fastdfs.NameValuePair;
import com.june.fastdfs.StorePath;

/**
 * @author junehappylove
 *
 */
public interface IStorageClientService {

	/**
	 * 上传不可修改的文件
	 * 
	 * @param groupName
	 *            分组
	 * @param ins
	 *            输入流
	 * @param size
	 *            大小
	 * @param ext
	 *            扩展名/后缀名/文件类型
	 * @return StorePath对象
	 */
	StorePath uploadFile(String groupName, InputStream ins, long size, String ext);

	/**
	 * 上传可修改的文件
	 * 
	 * @param groupName
	 *            分组
	 * @param ins
	 *            输入流
	 * @param size
	 *            大小
	 * @param ext
	 *            扩展名/后缀名/文件类型
	 * @return StorePath对象
	 */
	StorePath uploadAppenderFile(String groupName, InputStream ins, long size, String ext);

	/**
	 * 上传从Slave的文件
	 * 
	 * @param groupName
	 *            分组
	 * @param masterFilename
	 *            主master文件名
	 * @param ins
	 *            输入流
	 * @param size
	 *            大小
	 * @param prefixName
	 *            前缀
	 * @param ext
	 *            扩展名/后缀名/文件类型
	 * @return StorePath对象
	 */
	StorePath uploadSlaveFile(String groupName, String masterFilename, InputStream ins, long size, String prefixName,
			String ext);

	/**
	 * 可修改文件添加内容
	 * 
	 * @param path
	 *            路径
	 * @param ins
	 *            输入流
	 * @param size
	 *            大小
	 */
	void appendFile(String groupName, String path, InputStream ins, long size);

	/**
	 * 修改可修改文件的内容
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @param offset
	 *            偏移
	 * @param ins
	 *            输入流
	 * @param size
	 *            大小
	 */
	void modifyFile(String groupName, String path, long offset, InputStream ins, long size);

	/**
	 * 删除文件
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 */
	void deleteFile(String groupName, String path);

	/**
	 * 清除appender类型文件的内容
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 */
	void truncateFile(String groupName, String path, long truncatedFileSize);

	/**
	 * 下载整个文件
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @param handling
	 *            处理器
	 * @return 处理器数据
	 */
	<T> T downloadFile(String groupName, String path, IFdfsFileInputStreamHandler<T> handling);

	/**
	 * 下载文件片段
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @param offset
	 *            偏移
	 * @param size
	 *            大小
	 * @param handling
	 *            处理器
	 * @return 处理器数据
	 */
	<T> T downloadFile(String groupName, String path, long offset, long size, IFdfsFileInputStreamHandler<T> handling);

	/**
	 * 获取文件元信息
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @return 元数据
	 */
	NameValuePair[] getMetadata(String groupName, String path);

	/**
	 * 修改文件元信息（覆盖）
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @param metaList
	 *            元数据
	 */
	void overwriteMetadata(String groupName, String path, NameValuePair[] metaList);

	/**
	 * 修改文件元信息（合并）
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @param metaList
	 *            元数据
	 */
	void mergeMetadata(String groupName, String path, NameValuePair[] metaList);

	/**
	 * 查看文件的信息
	 * 
	 * @param groupName
	 *            组名
	 * @param path
	 *            路径
	 * @return 文件信息
	 */
	FileInfo queryFileInfo(String groupName, String path);
}
