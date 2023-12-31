package com.earlyou.aom.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ProjectBiz;
import com.earlyou.aom.vo.ProjectVO;

@SpringBootTest
class ProjectReadTests {
	
	@Autowired
	ProjectBiz projectbiz;

	@Test
	void contextLoads() {
		
		ProjectVO project = null;
		
		try {
			project = projectbiz.get("test project name");
			System.out.println(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
