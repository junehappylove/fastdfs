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
public class GroupStateBuilder {

	private static final int FIELD_INDEX_GROUP_NAME = 0;
	private static final int FIELD_INDEX_TOTAL_MB = 1;
	private static final int FIELD_INDEX_FREE_MB = 2;
	private static final int FIELD_INDEX_TRUNK_FREE_MB = 3;
	private static final int FIELD_INDEX_STORAGE_COUNT = 4;
	private static final int FIELD_INDEX_STORAGE_PORT = 5;
	private static final int FIELD_INDEX_STORAGE_HTTP_PORT = 6;
	private static final int FIELD_INDEX_ACTIVE_COUNT = 7;
	private static final int FIELD_INDEX_CURRENT_WRITE_SERVER = 8;
	private static final int FIELD_INDEX_STORE_PATH_COUNT = 9;
	private static final int FIELD_INDEX_SUBDIR_COUNT_PER_PATH = 10;
	private static final int FIELD_INDEX_CURRENT_TRUNK_FILE_ID = 11;

	private static final int fieldsTotalSize;
	private static final FieldDefinition[] fieldsArray = new FieldDefinition[12];

	static {
		int offset = 0;
		fieldsArray[FIELD_INDEX_GROUP_NAME] = new FieldDefinition(offset, OtherConstants.FDFS_GROUP_NAME_MAX_LEN + 1);
		offset += OtherConstants.FDFS_GROUP_NAME_MAX_LEN + 1;

		fieldsArray[FIELD_INDEX_TOTAL_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_FREE_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_TRUNK_FREE_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORAGE_COUNT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORAGE_PORT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORAGE_HTTP_PORT] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_ACTIVE_COUNT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_CURRENT_WRITE_SERVER] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_STORE_PATH_COUNT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_SUBDIR_COUNT_PER_PATH] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsArray[FIELD_INDEX_CURRENT_TRUNK_FILE_ID] = new FieldDefinition(offset,
				OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
		offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

		fieldsTotalSize = offset;
	}

	public static GroupState convert(byte[] bs, int offset, Charset charset) {
		GroupState state = new GroupState();

		state.setGroupName(StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_GROUP_NAME], charset));
		state.setTotalMB(StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MB]));
		state.setFreeMB(StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_FREE_MB]));
		state.setTrunkFreeMB(StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TRUNK_FREE_MB]));
		state.setStorageCount(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_COUNT]));
		state.setStoragePort(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_PORT]));
		state.setStorageHttpPort(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_HTTP_PORT]));
		state.setActiveCount(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_ACTIVE_COUNT]));
		state.setCurrentWriteServer(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CURRENT_WRITE_SERVER]));
		state.setStorePathCount(StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORE_PATH_COUNT]));
		state.setSubdirCountPerPath(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_SUBDIR_COUNT_PER_PATH]));
		state.setCurrentTrunkFileId(
				StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CURRENT_TRUNK_FILE_ID]));

		return state;
	}

	/**
	 * get fields total size
	 * 
	 * @return fields total size
	 */
	public static int getFieldsTotalSize() {
		return fieldsTotalSize;
	}
}
