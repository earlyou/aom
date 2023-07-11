package com.earlyou.aom.cpuinfo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.CpuBiz;
import com.earlyou.aom.vo.CpuVO;

@SpringBootTest
class CpuinfoReadAllTests {

	@Autowired
	CpuBiz cpubiz;

	@Test
	void contextLoads() {

		List<CpuVO> list = null;

		try {
			list = cpubiz.get();
			if (list.isEmpty() == true) {
				System.out.println("no data");
			} else {
				for (CpuVO obj : list) {
					System.out.println(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}