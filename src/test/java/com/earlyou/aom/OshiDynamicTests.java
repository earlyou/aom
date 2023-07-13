package com.earlyou.aom;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessFiltering;
import oshi.software.os.OperatingSystem.ProcessSorting;
import oshi.util.FormatUtil;


@SpringBootTest
public class OshiDynamicTests {
	
	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		OperatingSystem os = si.getOperatingSystem();
		CentralProcessor processor = hal.getProcessor();
		GlobalMemory memory = hal.getMemory();
		List<HWDiskStore> list = hal.getDiskStores();
		Sensors sensors = hal.getSensors();
		FileSystem fileSystem = os.getFileSystem();
		
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		
		
		double totmem = Math.round(hal.getMemory().getTotal()/(1024.0*1024.0*1024.0)*100)/100.0;
		
		try {
			for (int j = 0; j < 100; j++) {
				System.out.println("***********************************************************");
				System.out.println("**********************  Uptime  **********************");
				String uptime = FormatUtil.formatElapsedSecs(os.getSystemUptime());								// 10 days, 18:06:10
				
				System.out.println("**********************  Memory  **********************");
				double availmem = Math.round(memory.getAvailable()/(1024.0*1024.0*1024.0)*100)/100.0;			// 7.7 GB
				double usagemem = Math.round((totmem - availmem)*100)/100.0;									// 8.4 GB
				double memratio = Math.round(usagemem/totmem*10000)/100.0;										// 53.01
				
				System.out.println("**********************  Sensors  **********************");
				double cputemp = sensors.getCpuTemperature();
				double cpuvolt = sensors.getCpuVoltage();
				int[] fanspeed = sensors.getFanSpeeds();
				
				System.out.println("**********************  Usable Space  **********************");
				List<Double> usable = null;
				for (OSFileStore fs : fileSystem.getFileStores()) {
					usable.add(Math.round(fs.getUsableSpace()/(1024.0*1024.0*1024.0)*100)/100.0);				// 15.47 GB								// 로컬 고정 디스크 (C:)
				}
				
				System.out.println("**********************  CPU Usage  **********************");
				double cpuUsage = processor.getSystemCpuLoadBetweenTicks(prevTicks);							// 0.5%
				prevTicks = processor.getSystemCpuLoadTicks();
				System.out.println(cpuUsage);
	            for (double avg : processor.getProcessorCpuLoad(1000)) {
	    			System.out.println(String.format(" %.1f%%", avg*100.0));									// 0.0% 0.0% 0.0% 0.0% 3.1% 0.0%
	    		}
	            
	            System.out.println("**********************  Processess  **********************");
	            int proccount = os.getProcessCount();															// 231
	            int thdcount = os.getThreadCount();																// 3305
	            
	            
	            List<OSProcess> procs = os.getProcesses(ProcessFiltering.ALL_PROCESSES, ProcessSorting.CPU_DESC, 5);
	            System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
	    		for (int i = 0; i < procs.size(); i++) {
	    			OSProcess p = procs.get(i);
	    			System.out.println(String.format(" %5d %5.1f %4.1f %9s %9s %s", p.getProcessID(),
	    					100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
	    					100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
	    					FormatUtil.formatBytes(p.getResidentSetSize()), p.getName()));
	    		}
		        
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}