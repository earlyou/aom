package com.earlyou.aom.filestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.FilestoreBiz;
import com.earlyou.aom.vo.FilestoreVO;

import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

@SpringBootTest
class FilestoreDeleteTests {

	@Autowired
	FilestoreBiz filestorebiz;

	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		OperatingSystem os = si.getOperatingSystem();
		FileSystem fileSystem = os.getFileSystem();
		
		try {
			filestorebiz.removeall();
			for (OSFileStore fs : fileSystem.getFileStores()) {
				String fsmnt = fs.getMount();
				double fstot = fs.getTotalSpace();
				String fstype = fs.getType();
				FilestoreVO obj = new FilestoreVO(fsmnt, fstot, fstype);
				filestorebiz.register(obj);
			}
			filestorebiz.remove("Z:\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
