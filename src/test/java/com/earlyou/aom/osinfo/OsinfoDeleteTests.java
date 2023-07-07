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
class OsinfoDeleteTests {

	@Autowired
	OsinfoBiz osinfobiz;

	@Test
	void contextLoads() {

		SystemInfo si = new SystemInfo();

		OperatingSystem os = si.getOperatingSystem();

		String osinfo = os.toString();
		String booted = String.valueOf(Instant.ofEpochSecond(os.getSystemBootTime()));
		booted = booted.replace("T", " ").replace("Z", "");
		
		OsinfoVO obj = new OsinfoVO(osinfo, booted);
		
		try {
			osinfobiz.removeall();
			osinfobiz.register(obj);
			osinfobiz.remove(obj.getOs());
			osinfobiz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
