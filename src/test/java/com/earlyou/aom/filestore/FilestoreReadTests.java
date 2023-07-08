package com.earlyou.aom.filestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.FilestoreBiz;
import com.earlyou.aom.vo.FilestoreVO;

@SpringBootTest
class FilestoreReadTests {

	@Autowired
	FilestoreBiz filestorebiz;

	@Test
	void contextLoads() {
		
		FilestoreVO obj = null;
		
		try {
			obj = filestorebiz.get("C:\\");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
