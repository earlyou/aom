package com.earlyou.aom.ram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.RamBiz;
import com.earlyou.aom.vo.RamVO;

@SpringBootTest
class RamReadTests {

	@Autowired
	RamBiz rambiz;

	@Test
	void contextLoads() {
		
		RamVO info = null;
		
		try {
			info = rambiz.get("BANK 0");
			System.out.println(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
