/**
 * 
 */
package com.june.fastdfs;

import lombok.Data;

/**
 * fastdfs中group的状态信息<br>
 * 
 * @author junehappylove
 *
 */
@Data
public class GroupState {

	/** Group的名称 */
	private String groupName;
	
	/** 总存储空间 */
	private long totalMB;
	
	/** 剩余空间 */
	private long freeMB; 
	
	/** trunk剩余空间 */
	private long trunkFreeMB;
	
	/** 存储节点数量 */
	private int storageCount;
	
	/** 存储节点端口 */
	private int storagePort;
	
	/** 存储服务器的HTTP端口 */
	private int storageHttpPort;
	
	/** 处于活动状态的存储节点 */
	private int activeCount;
	
	/** 目前用于上传文件的存储节点的索引 */
	private int currentWriteServer;
	
	/** 每个存储节点的存储路径个数 */
	private int storePathCount;
	
	/** 每个存储录几个的子文件夹个数 */
	private int subdirCountPerPath; 
	
	/** 当前trunk文件的id */
	private int currentTrunkFileId;

}
