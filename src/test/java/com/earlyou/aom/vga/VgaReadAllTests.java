package com.earlyou.aom.vga;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.VgaBiz;
import com.earlyou.aom.vo.VgaVO;

@SpringBootTest
class VgaReadAllTests {

	@Autowired
	VgaBiz vgabiz;

	@Test
	void contextLoads() {
		
		List<VgaVO> list = null;
		
		try {
			list = vgabiz.get();
			for (VgaVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}