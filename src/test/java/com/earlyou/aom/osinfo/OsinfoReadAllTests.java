package com.earlyou.aom.osinfo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.OsinfoBiz;
import com.earlyou.aom.vo.OsinfoVO;

@SpringBootTest
class OsinfoReadAllTests {

	@Autowired
	OsinfoBiz osinfobiz;

	@Test
	void contextLoads() {
		
		List<OsinfoVO> list = null;
		
		try {
			list = osinfobiz.get();
			for (OsinfoVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}