package com.earlyou.aom.osinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.OsinfoBiz;
import com.earlyou.aom.vo.OsinfoVO;

@SpringBootTest
class OsinfoReadTests {

	@Autowired
	OsinfoBiz osinfobiz;

	@Test
	void contextLoads() {
		
		OsinfoVO info = null;
		
		try {
			info = osinfobiz.get("Microsoft Windows 11 build 22621");
			System.out.println(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
