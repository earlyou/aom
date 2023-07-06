package com.earlyou.aom.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ProjectBiz;
import com.earlyou.aom.vo.ProjectVO;

@SpringBootTest
class ProjectCreateTests {
	
	@Autowired
	ProjectBiz projectbiz;

	@Test
	void contextLoads() {
		ProjectVO proj = new ProjectVO("test project name", "testID", "test domain","2023-01-23");
		
		try {
			projectbiz.register(proj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
