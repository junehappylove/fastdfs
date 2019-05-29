/**
 * 
 */
package com.june.fastdfs;

import java.nio.charset.Charset;

import com.june.fastdfs.proto.OtherConstants;

/**
 * @author junehappylove
 *
 */
public class StorageStateBuilder {

	protected static final int FIELD_INDEX_STATUS = 0;
	protected static final int FIELD_INDEX_ID = 1;
	protected static final int FIELD_INDEX_IP_ADDR = 2;
	protected static final int FIELD_INDEX_DOMAIN_NAME = 3;
	protected static final int FIELD_INDEX_SRC_IP_ADDR = 4;
	protected static final int FIELD_INDEX_VERSION = 5;
	protected static final int FIELD_INDEX_JOIN_TIME = 6;
	protected static final int FIELD_INDEX_UP_TIME = 7;
	protected static final int FIELD_INDEX_TOTAL_MB = 8;
	protected static final int FIELD_INDEX_FREE_MB = 9;
	protected static final int FIELD_INDEX_UPLOAD_PRIORITY = 10;
	protected static final int FIELD_INDEX_STORE_PATH_COUNT = 11;
	protected static final int FIELD_INDEX_SUBDIR_COUNT_PER_PATH = 12;
	protected static final int FIELD_INDEX_CURRENT_WRITE_PATH = 13;
	protected static final int FIELD_INDEX_STORAGE_PORT = 14;
	protected static final int FIELD_INDEX_STORAGE_HTTP_PORT = 15;

	protected static final int FIELD_INDEX_CONNECTION_ALLOC_COUNT = 16;
	protected static final int FIELD_INDEX_CONNECTION_CURRENT_COUNT = 17;
	protected static final int FIELD_INDEX_CONNECTION_MAX_COUNT = 18;

	protected static final int FIELD_INDEX_TOTAL_UPLOAD_COUNT = 19;
	protected static final int FIELD_INDEX_SUCCESS_UPLOAD_COUNT = 20;
	protected static final int FIELD_INDEX_TOTAL_APPEND_COUNT = 21;
	protected static final int FIELD_INDEX_SUCCESS_APPEND_COUNT = 22;
	protected static final int FIELD_INDEX_TOTAL_MODIFY_COUNT = 23;
	protected static final int FIELD_INDEX_SUCCESS_MODIFY_COUNT = 24;
	protected static final int FIELD_INDEX_TOTAL_TRUNCATE_COUNT = 25;
	protected static final int FIELD_INDEX_SUCCESS_TRUNCATE_COUNT = 26;
	protected static final int FIELD_INDEX_TOTAL_SET_META_COUNT = 27;
	protected static final int FIELD_INDEX_SUCCESS_SET_META_COUNT = 28;
	protected static final int FIELD_INDEX_TOTAL_DELETE_COUNT = 29;
	protected static final int FIELD_INDEX_SUCCESS_DELETE_COUNT = 30;
	protected static final int FIELD_INDEX_TOTAL_DOWNLOAD_COUNT = 31;
	protected static final int FIELD_INDEX_SUCCESS_DOWNLOAD_COUNT = 32;
	protected static final int FIELD_INDEX_TOTAL_GET_META_COUNT = 33;
	protected static final int FIELD_INDEX_SUCCESS_GET_META_COUNT = 34;
	protected static final int FIELD_INDEX_TOTAL_CREATE_LINK_COUNT = 35;
	protected static final int FIELD_INDEX_SUCCESS_CREATE_LINK_COUNT = 36;
	protected static final int FIELD_INDEX_TOTAL_DELETE_LINK_COUNT = 37;
	protected static final int FIELD_INDEX_SUCCESS_DELETE_LINK_COUNT = 38;
	protected static final int FIELD_INDEX_TOTAL_UPLOAD_BYTES = 39;
	protected static final int FIELD_INDEX_SUCCESS_UPLOAD_BYTES = 40;
	protected static final int FIELD_INDEX_TOTAL_APPEND_BYTES = 41;
	protected static final int FIELD_INDEX_SUCCESS_APPEND_BYTES = 42;
	protected static final int FIELD_INDEX_TOTAL_MODIFY_BYTES = 43;
	protected static final int FIELD_INDEX_SUCCESS_MODIFY_BYTES = 44;
	protected static final int FIELD_INDEX_TOTAL_DOWNLOAD_BYTES = 45;
	protected static final int FIELD_INDEX_SUCCESS_DOWNLOAD_BYTES = 46;
	protected static final int FIELD_INDEX_TOTAL_SYNC_IN_BYTES = 47;
	protected static final int FIELD_INDEX_SUCCESS_SYNC_IN_BYTES = 48;
	protected static final int FIELD_INDEX_TOTAL_SYNC_OUT_BYTES = 49;
	protected static final int FIELD_INDEX_SUCCESS_SYNC_OUT_BYTES = 50;
	protected static final int FIELD_INDEX_TOTAL_FILE_OPEN_COUNT = 51;
	protected static final int FIELD_INDEX_SUCCESS_FILE_OPEN_COUNT = 52;
	protected static final int FIELD_INDEX_TOTAL_FILE_READ_COUNT = 53;
	protected static final int FIELD_INDEX_SUCCESS_FILE_READ_COUNT = 54;
	protected static final int FIELD_INDEX_TOTAL_FILE_WRITE_COUNT = 55;
	protected static final int FIELD_INDEX_SUCCESS_FILE_WRITE_COUNT = 56;
	protected static final int FIELD_INDEX_LAST_SOURCE_UPDATE = 57;
	protected static final int FIELD_INDEX_LAST_SYNC_UPDATE = 58;
	protected static final int FIELD_INDEX_LAST_SYNCED_TIMESTAMP = 59;
	protected static final int FIELD_INDEX_LAST_HEART_BEAT_TIME = 60;
	protected static final int FIELD_INDEX_IF_TRUNK_FILE = 61;

	private static int fieldsTotalSize;
	private static FieldDefinition[] fieldsArray = new FieldDefinition[62];

	static {
		int offset = 0;

		fieldsArray[FIELD_INDEX_STATUS] = new FieldDefinition(offset, 1);
		offset += 1;

		fieldsArray[FIELD_INDEX_ID] = new FieldDefinition(offset, OtherConstants.FDFS_STORAGE_ID_MAX_SIZE);
		offset += OtherConstants.FDFS_STORAGE_ID_MAX_SIZE;

		fieldsArray[FIELD_INDEX_IP_ADDR] = new FieldDefinition(offset, OtherConstants.FDFS_IPADDR_SIZE);
		offset += OtherConstants.FDFS_IPADDR_SIZE;

		fieldsArray[FIELD_INDEX_DOMAIN_NAME] = new FieldDefinition(offset, OtherConstants.FDFS_DOMAIN_NAME_MAX_SIZE);
		offset += OtherConstants.FDFS_DOMAIN_NAME_MAX_SIZE;

		fieldsArray[FIELD_INDEX_SRC_IP_ADDR] = new FieldDefinition(offset, OtherConstants.FDFS_IPADDR_SIZE);
		offset += OtherConstants.FDFS_IPADDR_SIZE;

		fieldsArray[FIELD_INDEX_VERSION] = new FieldDefinition(offset, OtherConstants.FDFS_VERSION_SIZE);
		offset += OtherConstants.FDFS_VERSION_SIZE;

		fieldsArray[FIELD_INDEX_JOIN_TIME] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_UP_TIME] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_FREE_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_UPLOAD_PRIORITY] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORE_PATH_COUNT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUBDIR_COUNT_PER_PATH] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_CURRENT_WRITE_PATH] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORAGE_PORT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORAGE_HTTP_PORT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_CONNECTION_ALLOC_COUNT] = new FieldDefinition(offset, 4);
		offset += 4;

		fieldsArray[FIELD_INDEX_CONNECTION_CURRENT_COUNT] = new FieldDefinition(offset, 4);
		offset += 4;

		fieldsArray[FIELD_INDEX_CONNECTION_MAX_COUNT] = new FieldDefinition(offset, 4);
		offset += 4;

		fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_APPEND_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_APPEND_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_MODIFY_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_TRUNCATE_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_TRUNCATE_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_SET_META_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_SET_META_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_DELETE_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_DELETE_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_GET_META_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_GET_META_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_CREATE_LINK_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_CREATE_LINK_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_DELETE_LINK_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_DELETE_LINK_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_APPEND_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_APPEND_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_MODIFY_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_SYNC_IN_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_SYNC_IN_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_SYNC_OUT_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_SYNC_OUT_BYTES] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_FILE_OPEN_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_FILE_OPEN_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_FILE_READ_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_FILE_READ_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TOTAL_FILE_WRITE_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUCCESS_FILE_WRITE_COUNT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_LAST_SOURCE_UPDATE] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_LAST_SYNC_UPDATE] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_LAST_SYNCED_TIMESTAMP] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_LAST_HEART_BEAT_TIME] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_IF_TRUNK_FILE] = new FieldDefinition(offset, 1);
		offset += 1;

		fieldsTotalSize = offset;
	}

	public static StorageState convert(byte[] bs, int offset, Charset charset) {
		StorageState state = new StorageState();

		state.setStatus(StateBuilderHelper.byteValue(bs, offset, fieldsArray[FIELD_INDEX_STATUS]));
		state.setId(StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_ID], charset));
		state.setIpAddr(StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_IP_ADDR], charset));
		state.setSrcIpAddr(StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_SRC_IP_ADDR], charset));
		state.setDomainName(StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_DOMAIN_NAME], charset));
		state.setVersion(StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_VERSION], charset));
		state.setTotalMB(StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MB]));
		state.setFreeMB(StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_FREE_MB]));
		state.setUploadPriority(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_UPLOAD_PRIORITY]));
		state.setJoinTime(StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_JOIN_TIME]));
		state.setUpTime(StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_UP_TIME]));
		state.setStorePathCount(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORE_PATH_COUNT]));
		state.setSubdirCountPerPath(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_SUBDIR_COUNT_PER_PATH]));
		state.setStoragePort(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_PORT]));
		state.setStorageHttpPort(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_HTTP_PORT]));
		state.setConnectionAllocCount(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CONNECTION_ALLOC_COUNT]));
		state.setConnectionCurrentCount(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CONNECTION_CURRENT_COUNT]));
		state.setConnectionMaxCount(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CONNECTION_MAX_COUNT]));
		state.setCurrentWritePath(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CURRENT_WRITE_PATH]));
		state.setTotalUploadCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_COUNT]));
		state.setSuccessUploadCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_COUNT]));
		state.setTotalAppendCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_APPEND_COUNT]));
		state.setSuccessAppendCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_APPEND_COUNT]));
		state.setTotalModifyCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MODIFY_COUNT]));
		state.setSuccessModifyCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_COUNT]));
		state.setTotalTruncateCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_TRUNCATE_COUNT]));
		state.setSuccessTruncateCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_TRUNCATE_COUNT]));
		state.setTotalSetMetaCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_SET_META_COUNT]));
		state.setSuccessSetMetaCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_SET_META_COUNT]));
		state.setTotalDeleteCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_DELETE_COUNT]));
		state.setSuccessDeleteCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_DELETE_COUNT]));
		state.setTotalDownloadCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_COUNT]));
		state.setSuccessDownloadCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_COUNT]));
		state.setTotalGetMetaCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_GET_META_COUNT]));
		state.setSuccessGetMetaCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_GET_META_COUNT]));
		state.setTotalCreateLinkCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_CREATE_LINK_COUNT]));
		state.setSuccessCreateLinkCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_CREATE_LINK_COUNT]));
		state.setTotalDeleteLinkCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_DELETE_LINK_COUNT]));
		state.setSuccessDeleteLinkCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_DELETE_LINK_COUNT]));
		state.setTotalUploadBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_BYTES]));
		state.setSuccessUploadBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_BYTES]));
		state.setTotalAppendBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_APPEND_BYTES]));
		state.setSuccessAppendBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_APPEND_BYTES]));
		state.setTotalModifyBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MODIFY_BYTES]));
		state.setSuccessModifyBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_BYTES]));
		state.setTotalDownloadloadBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_BYTES]));
		state.setSuccessDownloadloadBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_BYTES]));
		state.setTotalSyncInBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_SYNC_IN_BYTES]));
		state.setSuccessSyncInBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_SYNC_IN_BYTES]));
		state.setTotalSyncOutBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_SYNC_OUT_BYTES]));
		state.setSuccessSyncOutBytes(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_SYNC_OUT_BYTES]));
		state.setTotalFileOpenCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_FILE_OPEN_COUNT]));
		state.setSuccessFileOpenCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_FILE_OPEN_COUNT]));
		state.setTotalFileReadCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_FILE_READ_COUNT]));
		state.setSuccessFileReadCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_FILE_READ_COUNT]));
		state.setTotalFileWriteCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_FILE_WRITE_COUNT]));
		state.setSuccessFileWriteCount(
				StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_SUCCESS_FILE_WRITE_COUNT]));
		state.setLastSourceUpdate(
				StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_LAST_SOURCE_UPDATE]));
		state.setLastSyncUpdate(StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_LAST_SYNC_UPDATE]));
		state.setLastSyncedTimestamp(
				StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_LAST_SYNCED_TIMESTAMP]));
		state.setLastHeartBeatTime(
				StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_LAST_HEART_BEAT_TIME]));
		state.setTrunkServer(StateBuilderHelper.booleanValue(bs, offset, fieldsArray[FIELD_INDEX_IF_TRUNK_FILE]));

		return state;
	}

	public static int getFieldsTotalSize() {
		return fieldsTotalSize;
	}

}
