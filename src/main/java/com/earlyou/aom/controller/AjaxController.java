package com.earlyou.aom.controller;

import java.lang.management.ManagementFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.management.OperatingSystemMXBean;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@RestController
public class AjaxController {

	@GetMapping("getcpu")
	public double getcpu() {
		/** using OperatingSystemMXBean */
		OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		double cpuUsage = Math.round(osbean.getCpuLoad()*1000.0)/10.0;
		
		return cpuUsage;
	}
	
	@GetMapping("getmem")
	public double getmem() {
		/** using Oshi Library */
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		GlobalMemory memory = hal.getMemory();
		double totmem = Math.round(hal.getMemory().getTotal()/(1024.0*1024.0*1024.0)*100)/100.0;
		
		double availmem = Math.round(memory.getAvailable()/(1024.0*1024.0*1024.0)*100)/100.0;
		double usagemem = Math.round((totmem - availmem)*100)/100.0;
		double memratio = Math.round(usagemem/totmem*1000)/10.0;
		
		return memratio;
	}
}
