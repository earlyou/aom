package com.earlyou.aom.project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.AdminBiz;
import com.earlyou.aom.vo.AdminVO;

@SpringBootTest
class ProjectReadAllTests {
	
	@Autowired
	AdminBiz adminbiz;

	@Test
	void contextLoads() {
		
		List<AdminVO> list = null;
		
		try {
			list = adminbiz.get();
			for (AdminVO admin : list) {
				System.out.println(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}