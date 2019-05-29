/**
 * 
 */
package com.june.fastdfs.service;

import com.june.fastdfs.GroupState;
import com.june.fastdfs.StorageClient;
import com.june.fastdfs.StorageState;

/**
 * @author junehappylove
 *
 */
public interface ITrackerClientService {

	StorageClient getStoreStorage();

	StorageClient getStoreStorage(String groupName);

	StorageClient getFetchStorage(String groupName, String path);

	StorageClient getUpdateStorage(String groupName, String path);

	GroupState[] listGroups();

	StorageState[] listStorages(String groupName);

	StorageState[] listStorages(String groupName, String storageIpAddr);

	void deleteStorage(String groupName, String storageIpAddr);
}
