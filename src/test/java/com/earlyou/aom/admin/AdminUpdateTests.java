package com.earlyou.aom.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.AdminBiz;
import com.earlyou.aom.vo.AdminVO;

@SpringBootTest
class AdminUpdateTests {
	
	@Autowired
	AdminBiz adminbiz;

	@Test
	void contextLoads() {
		String testinfo = "This is Updated Information";
		AdminVO admin = new AdminVO("testID", false, "updatepwd", "updatename", "M", 1990, 1, 1, "update@email.com", 82, "01012341234", "update location", testinfo);
		
		try {
			adminbiz.modify(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
