/**
 * 
 */
package com.june.fastdfs;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件的基础信息<br>
 * 
 * @author junehappylove
 *
 */
@Data
@NoArgsConstructor
@lombok.AllArgsConstructor
public class FileInfo {

	private String sourceIpAddr;
	private long size;
	private int createTime;
	private int crc32;

}
