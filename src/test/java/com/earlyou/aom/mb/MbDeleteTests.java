package com.earlyou.aom.mb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.MbBiz;
import com.earlyou.aom.vo.MbVO;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;

@SpringBootTest
class MbDeleteTests {

	@Autowired
	MbBiz mbbiz;

	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		ComputerSystem com = hal.getComputerSystem();
		Baseboard mb = com.getBaseboard();
		
		String mbsn = mb.getSerialNumber();
		String mbmfr = mb.getManufacturer();
		String mbmd = mb.getModel();
		String mbvs = mb.getVersion();
		if (mbvs.isBlank()) {
			mbvs = com.getFirmware().getVersion();
		}
		
		MbVO obj = new MbVO(mbsn, mbmfr, mbmd, mbvs);
		
		try {
			mbbiz.removeall();
			mbbiz.register(obj);
			mbbiz.remove(obj.getMbsn());
			mbbiz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
