package com.earlyou.aom.ram;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.RamBiz;
import com.earlyou.aom.vo.RamVO;

@SpringBootTest
class RamReadAllTests {

	@Autowired
	RamBiz rambiz;

	@Test
	void contextLoads() {
		
		List<RamVO> list = null;
		
		try {
			list = rambiz.get();
			for (RamVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}