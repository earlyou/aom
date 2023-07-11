package com.earlyou.aom.cpuinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.CpuBiz;
import com.earlyou.aom.vo.CpuVO;

@SpringBootTest
class CpuinfoReadTests {

	@Autowired
	CpuBiz cpubiz;

	@Test
	void contextLoads() {
		
		CpuVO info = null;
		
		try {
			info = cpubiz.get("Intel(R) Core(TM) i5-9400F CPU @ 2.90GHz");
			System.out.println(info == null);
			System.out.println(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
