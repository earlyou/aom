package com.earlyou.aom;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;



@SpringBootTest
public class OshiDynamicTests {
	
	@Test
	void contextLoads() {
//		SystemInfo si = new SystemInfo();
//
//		HardwareAbstractionLayer hal = si.getHardware();
//		OperatingSystem os = si.getOperatingSystem();
//		
//		long[] prevTicks = new long[TickType.values().length];
//		
//		
//		double totmem = Math.round(hal.getMemory().getTotal()/(1024.0*1024.0*1024.0)*100)/100.0;
//		
//		try {
//			for (int i = 0; i < 100; i++) {
//				System.out.println("***********************************************************");
//				String uptime = FormatUtil.formatElapsedSecs(os.getSystemUptime());								// 10 days, 18:06:10
//				double availmem = Math.round(hal.getMemory().getAvailable()/(1024.0*1024.0*1024.0)*100)/100.0;	// 7.7
//				double usagemem = Math.round((totmem - availmem)*100)/100.0;									// 8.4
//				double memratio = Math.round(usagemem/totmem*10000)/100.0;										// 53.01
//				
//				double[] first = hal.getProcessor().getProcessorCpuLoad(2000);
//				double[] second = hal.getProcessor().getProcessorCpuLoadBetweenTicks(hal.getProcessor().getProcessorCpuLoadTicks());
//				double third = hal.getProcessor().getSystemCpuLoad(2000);
//				double fourth = hal.getProcessor().getSystemCpuLoadBetweenTicks(hal.getProcessor().getSystemCpuLoadTicks());
//				double[] fifth = hal.getProcessor().getSystemLoadAverage(1);
//				double[] sixth = hal.getProcessor().getSystemLoadAverage(2);
//				double[] seventh = hal.getProcessor().getSystemLoadAverage(3);
//				
//				System.out.println(Arrays.toString(first));
//				System.out.println(Arrays.toString(second));
//				System.out.println(third);
//				System.out.println(fourth);
//				System.out.println(Arrays.toString(fifth));
//				System.out.println(Arrays.toString(sixth));
//				System.out.println(Arrays.toString(seventh));
//				
//				
//				double cpuLoad = hal.getProcessor().getSystemCpuLoadBetweenTicks(prevTicks) * 100;
//			    prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
//			    System.out.println("cpuLoad : " + cpuLoad);
//				
//				Thread.sleep(1000);
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
	}

}