package com.earlyou.aom.cpuinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.CpuBiz;
import com.earlyou.aom.vo.CpuVO;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@SpringBootTest
class CpuinfoCreateTests {
	
	@Autowired
	CpuBiz cpubiz;

	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		CentralProcessor cpu = hal.getProcessor();
		
		String cname = cpu.getProcessorIdentifier().getName();
		String carch = cpu.getProcessorIdentifier().getMicroarchitecture();
		int cpc = cpu.getPhysicalPackageCount();
		int cppc = cpu.getPhysicalProcessorCount();
		int clpc = cpu.getLogicalProcessorCount();
		
		CpuVO cpuobj = new CpuVO(cname, carch, cpc, cppc, clpc);
		
		try {
			cpubiz.register(cpuobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
