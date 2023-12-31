package com.earlyou.aom.project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ProjectBiz;
import com.earlyou.aom.vo.ProjectVO;

@SpringBootTest
class ProjectReadAllTests {
	
	@Autowired
	ProjectBiz projectbiz;

	@Test
	void contextLoads() {
		
		List<ProjectVO> list = null;
		
		try {
			list = projectbiz.get();
			for (ProjectVO project : list) {
				System.out.println(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}