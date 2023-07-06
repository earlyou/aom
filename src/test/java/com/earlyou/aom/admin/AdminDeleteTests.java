package com.earlyou.aom.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.AdminBiz;

@SpringBootTest
class AdminDeleteTests {
	
	@Autowired
	AdminBiz adminbiz;

	@Test
	void contextLoads() {
		String adminId = "testID";
		
		try {
			adminbiz.remove(adminId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
