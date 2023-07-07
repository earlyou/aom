package com.earlyou.aom.osinfo;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.OsinfoBiz;
import com.earlyou.aom.vo.OsinfoVO;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

@SpringBootTest
class OsinfoUpdateTests {

	@Autowired
	OsinfoBiz osinfobiz;

	@Test
	void contextLoads() {

		SystemInfo si = new SystemInfo();

		OperatingSystem os = si.getOperatingSystem();
		
		String booted = String.valueOf(Instant.ofEpochSecond(os.getSystemBootTime()));
		booted = booted.replace("T", " ").replace("Z", "");
		
		OsinfoVO obj = new OsinfoVO("Microsoft Windows 11 build 22621", booted);
		
		try {
			osinfobiz.modify(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
