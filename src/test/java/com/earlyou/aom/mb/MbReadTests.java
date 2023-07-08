package com.earlyou.aom.mb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.MbBiz;
import com.earlyou.aom.vo.MbVO;

@SpringBootTest
class MbReadTests {

	@Autowired
	MbBiz mbbiz;

	@Test
	void contextLoads() {
		
		MbVO info = null;
		
		try {
			info = mbbiz.get("M80-C2007700693");
			System.out.println(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
