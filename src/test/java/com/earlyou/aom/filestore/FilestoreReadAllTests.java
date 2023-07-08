package com.earlyou.aom.filestore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.FilestoreBiz;
import com.earlyou.aom.vo.FilestoreVO;

@SpringBootTest
class FilestoreReadAllTests {

	@Autowired
	FilestoreBiz filestorebiz;

	@Test
	void contextLoads() {
		
		List<FilestoreVO> list = null;
		
		try {
			list = filestorebiz.get();
			for (FilestoreVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}