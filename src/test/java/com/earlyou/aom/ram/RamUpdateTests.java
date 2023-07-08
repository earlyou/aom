package com.earlyou.aom.ram;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.RamBiz;
import com.earlyou.aom.vo.RamVO;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;

@SpringBootTest
class RamUpdateTests {

	@Autowired
	RamBiz rambiz;

	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		GlobalMemory memory = hal.getMemory();
		List<PhysicalMemory> mem = memory.getPhysicalMemory();
		
		
		
		try {
			for (PhysicalMemory pm : mem) {
				String mbank = pm.getBankLabel();
				double mcapa = pm.getCapacity();
				double mclock = pm.getClockSpeed();
				String mmfr = "update test manufacturer";
				String mtype = "test";
				RamVO obj = new RamVO(mbank, mcapa, mclock, mmfr, mtype);
				rambiz.modify(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
