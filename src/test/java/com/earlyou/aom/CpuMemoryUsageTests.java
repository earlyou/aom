package com.earlyou.aom;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CpuMemoryUsageTests {

	@Test
	void contextLoads() {
		try {
			OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

			for (int i = 0; i < 100; i++) {
				System.out.println("***********************************************************");
				double committedvirtualmemorysize = osbean.getCommittedVirtualMemorySize()/1024.0/1024.0/1024.0;
				double cpuload = osbean.getCpuLoad()*100.0;
				
				double totalmemorysize = osbean.getTotalMemorySize()/1024.0/1024.0/1024.0;
				double freememorysize = osbean.getFreeMemorySize()/1024.0/1024.0/1024.0;
				double memoryusage = totalmemorysize - freememorysize;
				double memoryload = (memoryusage/totalmemorysize)*100.0;
				
				double freeswapspacesize = osbean.getFreeSwapSpaceSize()/1024.0/1024.0/1024.0;
				double processcpuload = osbean.getProcessCpuLoad()*100.0;
				long processcputime = osbean.getProcessCpuTime();
				double totalswapspacesize = osbean.getTotalSwapSpaceSize()/1024.0/1024.0/1024.0;
				
				System.out.println("Committed Virtual Memory Size : " + String.format("%.2f", committedvirtualmemorysize) + " GB");
				System.out.println("Cpu Load : " + String.format("%.2f", cpuload) + " %");
				
				System.out.println("Total Memory Size : " + String.format("%.2f", totalmemorysize) + " GB");
				System.out.println("Free Memory Size : " + String.format("%.2f", freememorysize) + " GB");
				System.out.println("Memory Usage : " + String.format("%.2f", memoryusage) + " GB");
				System.out.println("Memory Load : " + String.format("%.2f", memoryload) + " %");
				
				System.out.println("Free Swap Space Size : " + String.format("%.2f", freeswapspacesize) + " GB");
				System.out.println("Process Cpu Load : " + String.format("%.2f", processcpuload) + " %");
				System.out.println("Process Cpu Time : " + processcputime);
				System.out.println("Total Swap Space Size : " + String.format("%.2f", totalswapspacesize) + " GB");
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}
