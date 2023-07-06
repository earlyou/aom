package com.earlyou.aom.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.AdminBiz;
import com.earlyou.aom.vo.AdminVO;

@SpringBootTest
class AdminCreateTests {
	
	@Autowired
	AdminBiz adminbiz;

	@Test
	void contextLoads() {
		String testinfo = "This is Test Information";
		AdminVO admin = new AdminVO("testID", false, "testpwd", "testname", "M", 1990, 1, 1, "test@email.com", 82, "01012341234", "test location", testinfo);
		
		try {
			adminbiz.register(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
