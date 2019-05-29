/**
 * 
 */
package com.june.fastdfs.proto;

/**
 * @author junehappylove
 *
 */
public class ProtoCommon {

	public static String getStorageStatusCaption(byte status) {
		switch (status) {
		case StatusConstants.FDFS_STORAGE_STATUS_INIT:
			return "INIT";
		case StatusConstants.FDFS_STORAGE_STATUS_WAIT_SYNC:
			return "WAIT_SYNC";
		case StatusConstants.FDFS_STORAGE_STATUS_SYNCING:
			return "SYNCING";
		case StatusConstants.FDFS_STORAGE_STATUS_IP_CHANGED:
			return "IP_CHANGED";
		case StatusConstants.FDFS_STORAGE_STATUS_DELETED:
			return "DELETED";
		case StatusConstants.FDFS_STORAGE_STATUS_OFFLINE:
			return "OFFLINE";
		case StatusConstants.FDFS_STORAGE_STATUS_ONLINE:
			return "ONLINE";
		case StatusConstants.FDFS_STORAGE_STATUS_ACTIVE:
			return "ACTIVE";
		case StatusConstants.FDFS_STORAGE_STATUS_NONE:
			return "NONE";
		default:
			return "UNKNOW";
		}
	}

}
