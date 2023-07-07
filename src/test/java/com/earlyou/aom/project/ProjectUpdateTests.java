package com.earlyou.aom.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ProjectBiz;
import com.earlyou.aom.vo.ProjectVO;

@SpringBootTest
class ProjectUpdateTests {
	
	@Autowired
	ProjectBiz projectbiz;

	@Test
	void contextLoads() {
		ProjectVO proj = new ProjectVO("test project name", "testID", "update.com", "update description", "2021-03-03");
		
		try {
			projectbiz.modify(proj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
