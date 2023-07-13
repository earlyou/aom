package com.earlyou.aom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@RestController
public class AjaxController {
	
	@GetMapping("getcpu")
	public double getcpu() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		CentralProcessor processor = hal.getProcessor();
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		double cpuUsage = processor.getSystemCpuLoadBetweenTicks(prevTicks);
		cpuUsage = Math.round(cpuUsage*1000)/10.0;
		return cpuUsage;
	}
}
