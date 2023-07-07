package com.earlyou.aom.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ProjectBiz;

@SpringBootTest
class ProjectDeleteTests {
	
	@Autowired
	ProjectBiz projectbiz;

	@Test
	void contextLoads() {
		String projectname = "test project name";
		
		try {
			projectbiz.remove(projectname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
