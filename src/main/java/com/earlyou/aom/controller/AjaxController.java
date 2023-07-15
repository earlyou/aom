package com.earlyou.aom.controller;

import java.lang.management.ManagementFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.management.OperatingSystemMXBean;

@RestController
public class AjaxController {

	@GetMapping("getcpu")
	public double getcpu() {
		OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		double cpuUsage = Math.round(osbean.getCpuLoad()*1000.0)/10.0;
		System.out.println(cpuUsage);
		
		return cpuUsage;
	}
}
