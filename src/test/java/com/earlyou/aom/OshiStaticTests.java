package com.earlyou.aom;

import java.time.Instant;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;



@SpringBootTest
public class OshiStaticTests {
	
	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		OperatingSystem os = si.getOperatingSystem();
		FileSystem fileSystem = os.getFileSystem();
		
		System.out.println("**********************  System  **********************");
		String osinfo = String.valueOf(os);																	// Microsoft Windows 11 build 22621
		String booted = String.valueOf(Instant.ofEpochSecond(os.getSystemBootTime()));						// 2023-06-22T11:32:26Z
		
		System.out.println("**********************  Mainboard  **********************");
		String mbsn = hal.getComputerSystem().getBaseboard().getSerialNumber();								// M80-C2007700693
		String mbmfr = hal.getComputerSystem().getBaseboard().getManufacturer();							// ASRock
		String mbmd = hal.getComputerSystem().getBaseboard().getModel();									// unknown
		String mbvs = hal.getComputerSystem().getBaseboard().getVersion();									//
		
		System.out.println("**********************  CPU  **********************");
		String cpunm = hal.getProcessor().getProcessorIdentifier().getName();								// Intel(R) Core(TM) i5-9400F CPU @ 2.90GHz
		int cpupg = hal.getProcessor().getPhysicalPackageCount();											// 1
		int cpupc = hal.getProcessor().getPhysicalProcessorCount();											// 6
		int cpulc = hal.getProcessor().getLogicalProcessorCount();											// 6
		String cpuarc = hal.getProcessor().getProcessorIdentifier().getMicroarchitecture();					// Coffee Lake
		
		System.out.println("**********************  Memory  **********************");
		double totmem = Math.round(hal.getMemory().getTotal()/(1024.0*1024.0*1024.0)*100)/100.0;			// 15.94 GB
		List<PhysicalMemory> mem = hal.getMemory().getPhysicalMemory();										// [all List about memories]
		for (PhysicalMemory pm : mem) {
			String mbank = pm.getBankLabel();																// BANK 0
			double mcapa = pm.getCapacity();																// 8.0 GB
			double mclock = pm.getClockSpeed();																// 2667.0 MHz
			String mmfr = pm.getManufacturer();																// Samsung
			String mtype = pm.getMemoryType();																// DDR4
		}
		
		System.out.println("**********************  Disks  **********************");
		List<HWDiskStore> dv =  hal.getDiskStores();														// [all List about drives]
		for (OSFileStore fs : fileSystem.getFileStores()) {
			String fsmnt = fs.getMount();																	// C:\
			double fstot = fs.getTotalSpace();															// 111.16015243530273 GB
			String fstype = fs.getType();																	// NTFS
		}
		
		System.out.println("**********************  Graphic Cards  **********************");
		List<GraphicsCard> vga = hal.getGraphicsCards();													// [GraphicsCard@65753724 [name=NVIDIA GeForce GTX 1080, deviceId=0x1b80, vendor=NVIDIA (0x10de), vRam=4293918720, versionInfo=[DriverVersion=31.0.15.3640]]]
		for (GraphicsCard gc : vga) {
			String gcid = gc.getDeviceId();																	// 0x1b80
			String gcnm = gc.getName();																		// NVIDIA GeForce GTX 1080
			String gcvd = gc.getVendor();																	// NVIDIA (0x10de)
			String gcvs = gc.getVersionInfo();																// DriverVersion=31.0.15.3640
			double gcvr = gc.getVRam();																		// 3.9990234375 GB
		}
	}

}