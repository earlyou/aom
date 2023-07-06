package com.earlyou.aom.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.AdminBiz;
import com.earlyou.aom.vo.AdminVO;

@SpringBootTest
class ProjectReadTests {
	
	@Autowired
	AdminBiz adminbiz;

	@Test
	void contextLoads() {
		
		AdminVO admin = null;
		
		try {
			admin = adminbiz.get("testId");
			System.out.println(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
