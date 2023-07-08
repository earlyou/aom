package com.earlyou.aom.vga;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.VgaBiz;
import com.earlyou.aom.vo.VgaVO;

@SpringBootTest
class VgaReadTests {

	@Autowired
	VgaBiz vgabiz;

	@Test
	void contextLoads() {
		
		VgaVO info = null;
		
		try {
			info = vgabiz.get("0x1b80");
			System.out.println(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
