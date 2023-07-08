package com.earlyou.aom.vga;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.VgaBiz;
import com.earlyou.aom.vo.VgaVO;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

@SpringBootTest
class VgaUpdateTests {

	@Autowired
	VgaBiz vgabiz;

	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		List<GraphicsCard> vga = hal.getGraphicsCards();	
		
		try {
			for (GraphicsCard gc : vga) {
				String gcid = gc.getDeviceId();
				String gcnm = gc.getName();
				String gcvd = gc.getVendor().substring(0, gc.getVendor().indexOf(" "));
				String gcvs = "test version";
				double gcvr = gc.getVRam();
				VgaVO obj = new VgaVO(gcid, gcnm, gcvd, gcvs, gcvr);
				vgabiz.modify(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
