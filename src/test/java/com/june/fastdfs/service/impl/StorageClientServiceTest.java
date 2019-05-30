package com.june.fastdfs.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.june.fastdfs.GroupState;
import com.june.fastdfs.StorageState;
import com.june.fastdfs.StorePath;
import com.june.fastdfs.service.IStorageClientService;
import com.june.fastdfs.service.ITrackerClientService;


@ContextConfiguration("classpath:spring-fdfs-test.xml")
public class StorageClientServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ITrackerClientService trackerClientService;

	@Autowired
	private IStorageClientService storageClientService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGroupState() {
		GroupState[] states = trackerClientService.listGroups();
		
		for (GroupState state : states) {
			StorageState[] storageStates = trackerClientService.listStorages(state.getGroupName());
			for (StorageState storageState : storageStates) {
				System.out.println(ToStringBuilder.reflectionToString(storageState));
			}
			System.out.println(ToStringBuilder.reflectionToString(state));
		}
	}
	
	@Test
	public void deleteFile(){
		String path = "group1/M00/00/00/wKggAlSeVECAf4O_AAFzGU8tJa0801.jpg";
		String group1 = path.substring(0, path.indexOf("/"));
		String p = path.substring(path.indexOf("/")+1,path.length());
		storageClientService.deleteFile(group1, p);
	}

	@Test
	public void testUploadFile() throws IOException {
		/*byte[] bytes = FileUtils
				.readFileToByteArray(new File(
						"f:/share/1.mp4"));*/
		
		FileInputStream fileInputStream = new FileInputStream("f:/share/1.jpg");
		
		/*StorePath storePath = null;
		for (int i = 0; i < 1000; i++) {
			storePath = storageClientService.uploadFile("group1",
					new ByteArrayInputStream(bytes), bytes.length, "jpg");
			System.out.println(i);
		}*/
		long begin = System.currentTimeMillis();
		
		StorePath storePath = storageClientService.uploadFile("group1",
				fileInputStream, (long)fileInputStream.available(), "jpg");
		System.out.println(storePath.getPath());
		System.out.println(storePath.getPath().length());
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}

	@Test
	public void testUploadSlaveFile() throws IOException {
		byte[] bytes = FileUtils.readFileToByteArray(new File(
				"f:/share/1.jpg"));
		StorePath storePath = storageClientService.uploadSlaveFile("image01",
				"M00/00/00/rBBynlPsiXeAdH8xASKM8lGiSWc346.png",
				new ByteArrayInputStream(bytes), bytes.length, "!png", "jpg");
		System.out.println(storePath);
	}
	
	@Test
	public void testDownloadFile() {
		byte[] bytes = storageClientService.downloadFile("image01",
				"M00/00/00/rBBynlPsiXeAdH8xASKM8lGiSWc346[png].jpg", new ByteArrayFdfsFileInputStreamHandler());
		System.out.println(bytes.length);
	}
	
	@Test
	public void testUploadMultiFile() throws IOException {
		int totalCount = 1000;
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);

		executor.afterPropertiesSet();
		final AtomicInteger failCount = new AtomicInteger(0);
		final AtomicInteger count = new AtomicInteger(0);
		for (int i = 0; i < totalCount; i++) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						byte[] bytes = FileUtils
								.readFileToByteArray(new File(
										"f:/share/1.jpg"));
						StorePath storePath = storageClientService.uploadFile(null,
								new ByteArrayInputStream(bytes), bytes.length, "jpg");
						System.out.println(storePath);
					} catch (Exception e) {
						e.printStackTrace();
						failCount.incrementAndGet();
					} finally {
						count.incrementAndGet();
					}

				}
			});

		}
		while (count.get() < totalCount) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
		executor.destroy();
		System.out.println("fail count: " + failCount.get());
	}

}
