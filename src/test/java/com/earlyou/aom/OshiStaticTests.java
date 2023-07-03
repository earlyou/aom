package com.earlyou.aom;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.software.os.OperatingSystem;



@SpringBootTest
public class OshiStaticTests {
	
	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		OperatingSystem os = si.getOperatingSystem();
		
		String osinfo = String.valueOf(os);																	// Microsoft Windows 11 build 22621
		String booted = String.valueOf(Instant.ofEpochSecond(os.getSystemBootTime()));						// 2023-06-22T11:32:26Z
		String bsbdmfr = hal.getComputerSystem().getBaseboard().getManufacturer();							// ASRock
		String bsbmdnm = hal.getComputerSystem().getBaseboard().getModel();									// unknown
		String bsbvsnm = hal.getComputerSystem().getBaseboard().getVersion();								//
		String cpunm = hal.getProcessor().getProcessorIdentifier().getName();								// Intel(R) Core(TM) i5-9400F CPU @ 2.90GHz
		int cpupg = hal.getProcessor().getPhysicalPackageCount();											// 1
		int cpupc = hal.getProcessor().getPhysicalProcessorCount();											// 6
		int cpulc = hal.getProcessor().getLogicalProcessorCount();											// 6
		String cpuarch = hal.getProcessor().getProcessorIdentifier().getMicroarchitecture();				// Coffee Lake
		double totmem = Math.round(hal.getMemory().getTotal()/(1024.0*1024.0*1024.0)*100)/100.0;			// 15.94
		List<PhysicalMemory> mem = hal.getMemory().getPhysicalMemory();										// [all List about memories]
		List<HWDiskStore> dv =  hal.getDiskStores();														// [all List about drives]
		String dvmd =  hal.getDiskStores().get(0).getModel();												// CT120BX500SSD1 (표준 디스크 드라이브)
		double dvsz = Math.round(hal.getDiskStores().get(0).getSize()/(1024.0*1024.0*1024.0)*100)/100.0;	// 111.79
		List<GraphicsCard> vga = hal.getGraphicsCards();													// [GraphicsCard@65753724 [name=NVIDIA GeForce GTX 1080, deviceId=0x1b80, vendor=NVIDIA (0x10de), vRam=4293918720, versionInfo=[DriverVersion=31.0.15.3640]]]
		String vganm = hal.getGraphicsCards().get(0).getName();												// NVIDIA GeForce GTX 1080
		String vgavd = hal.getGraphicsCards().get(0).getVendor();											// NVIDIA (0x10de)
		double vram = Math.round(hal.getGraphicsCards().get(0).getVRam()/(1024.0*1024.0*1024.0)*100)/100.0;	// 4.0
		String vgavs = hal.getGraphicsCards().get(0).getVersionInfo();										// DriverVersion=31.0.15.3640
	}

}