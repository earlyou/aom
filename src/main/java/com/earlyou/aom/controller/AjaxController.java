package com.earlyou.aom.controller;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.management.OperatingSystemMXBean;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

@RestController
@Slf4j
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

	@GetMapping("getfs")
	public List<Double> getfs() {
		SystemInfo si = new SystemInfo();

		OperatingSystem os = si.getOperatingSystem();
		FileSystem fileSystem = os.getFileSystem();
		
		List<Double> fsinfo = new ArrayList<Double>();
		double usable = 0.0;
		double totfs = 0.0;
		for (OSFileStore fs : fileSystem.getFileStores()) {
			if (fs != null) {
				usable = fs.getUsableSpace();
				totfs = fs.getTotalSpace();
				fsinfo.add(Math.round(usable/totfs*1000)/10.0);
			}
		}
		
		return fsinfo;
	}
	
	@GetMapping("gettemp")
	public double gettemp() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		Sensors sensors = hal.getSensors();
		
		double temp = 0.0;
		temp = sensors.getCpuTemperature();
		
		return temp;
	}
	
	@GetMapping("getfan")
	public int[] getfan() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		Sensors sensors = hal.getSensors();
		
		int[] fan = null;
		fan = sensors.getFanSpeeds();
		
		return fan;
	}
}
