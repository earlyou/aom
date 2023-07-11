package com.earlyou.aom;

import java.time.Instant;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.earlyou.aom.biz.CpuBiz;
import com.earlyou.aom.biz.FilestoreBiz;
import com.earlyou.aom.biz.MbBiz;
import com.earlyou.aom.biz.OsinfoBiz;
import com.earlyou.aom.biz.RamBiz;
import com.earlyou.aom.biz.VgaBiz;
import com.earlyou.aom.vo.CpuVO;
import com.earlyou.aom.vo.FilestoreVO;
import com.earlyou.aom.vo.MbVO;
import com.earlyou.aom.vo.OsinfoVO;
import com.earlyou.aom.vo.RamVO;
import com.earlyou.aom.vo.VgaVO;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

@Component
@Slf4j
public class AutoStart implements CommandLineRunner {

	@Autowired
	CpuBiz cpubiz;
	@Autowired
	FilestoreBiz fsbiz;
	@Autowired
	MbBiz mbbiz;
	@Autowired
	OsinfoBiz osbiz;
	@Autowired
	RamBiz rambiz;
	@Autowired
	VgaBiz vgabiz;

	@Override
	public void run(String... args) throws Exception {

		log.info("Reading System Information");
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		OperatingSystem os = si.getOperatingSystem();
		FileSystem fileSystem = os.getFileSystem();
		CentralProcessor cpu = hal.getProcessor();
		ComputerSystem com = hal.getComputerSystem();
		Baseboard mb = com.getBaseboard();
		GlobalMemory memory = hal.getMemory();
		List<PhysicalMemory> mem = memory.getPhysicalMemory();

		log.info("Reading System Databases");
		OsinfoVO oldos = osbiz.get().get(0);
		MbVO oldmb = mbbiz.get().get(0);
		CpuVO oldcpu = cpubiz.get().get(0);
		List<RamVO> oldmem = rambiz.get();
		List<FilestoreVO> oldfs = fsbiz.get();
		List<VgaVO> oldvga = vgabiz.get();

		// Osinfo
		String osinfo = os.toString();
		String booted = String.valueOf(Instant.ofEpochSecond(os.getSystemBootTime()));
		booted = booted.replace("T", " ").replace("Z", "");
		OsinfoVO newos = new OsinfoVO(osinfo, booted);
		if (oldos == null) {
			log.info("OS information not found");
			log.info("Registering new OS information");
			osbiz.removeall();
			osbiz.register(newos);
		} else if (oldos != newos) {
			log.info("Change in OS information detected");
			osbiz.removeall();
			osbiz.register(newos);
		} else if (oldos == newos) {
			log.info("OS information not changed");
		} else {
			log.info("ERROR in updating OS information");
		}

		// Motherboard
		String mbsn = mb.getSerialNumber();
		String mbmfr = mb.getManufacturer();
		String mbmd = mb.getModel();
		String mbvs = mb.getVersion();
		if (mbvs.isBlank()) {
			mbvs = com.getFirmware().getVersion();
		}
		MbVO newmb = new MbVO(mbsn, mbmfr, mbmd, mbvs);
		if (oldmb == null) {
			log.info("Motherboard information not found");
			log.info("Registering new Motherboard information");
			mbbiz.removeall();
			mbbiz.register(newmb);
		} else if (oldmb != newmb) {
			log.info("Change in Motherboard information detected");
			mbbiz.removeall();
			mbbiz.register(newmb);
		} else if (oldmb == newmb) {
			log.info("Motherboard information not changed");
		} else {
			log.info("ERROR in updating Motherboard information");
		}

		// CPU
		String cname = cpu.getProcessorIdentifier().getName();
		String carch = cpu.getProcessorIdentifier().getMicroarchitecture();
		int cpc = cpu.getPhysicalPackageCount();
		int cppc = cpu.getPhysicalProcessorCount();
		int clpc = cpu.getLogicalProcessorCount();
		CpuVO newcpu = new CpuVO(cname, carch, cpc, cppc, clpc);
		if (oldcpu == null) {
			log.info("CPU information not found");
			log.info("Registering new CPU information");
			cpubiz.removeall();
			cpubiz.register(newcpu);
		} else if (oldcpu != newcpu) {
			log.info("Change in CPU information detected");
			cpubiz.removeall();
			cpubiz.register(newcpu);
		} else if (oldcpu == newcpu) {
			log.info("CPU information not changed");
		} else {
			log.info("ERROR in updating CPU information");
		}

		// Memory
		List<RamVO> newmem = null;
		for (PhysicalMemory pm : mem) {
			String mbank = pm.getBankLabel();
			double mcapa = pm.getCapacity();
			double mclock = pm.getClockSpeed();
			String mmfr = pm.getManufacturer();
			String mtype = pm.getMemoryType();
			RamVO newram = new RamVO(mbank, mcapa, mclock, mmfr, mtype);
			newmem.add(newram);
		}
		if (oldmem == newmem) {
			log.info("Memory information not changed");
		} else if (oldmem.isEmpty() == true) {
			log.info("Memory information not found");
			log.info("Registering new Memory information");
			rambiz.removeall();
			for (RamVO ramVO : newmem) {
				rambiz.register(ramVO);
			}
		} else if (oldmem != newmem) {
			log.info("Change in Memory information detected");
			rambiz.removeall();
			for (RamVO ramVO : newmem) {
				rambiz.register(ramVO);
			}
		}

		// FileStore
		for (OSFileStore fs : fileSystem.getFileStores()) {
			String fsmnt = fs.getMount();
			double fstot = fs.getTotalSpace();
			String fstype = fs.getType();
		}

		List<GraphicsCard> vga = hal.getGraphicsCards();
		for (GraphicsCard gc : vga) {
			String gcid = gc.getDeviceId();
			String gcnm = gc.getName();
			String gcvd = gc.getVendor().substring(0, gc.getVendor().indexOf(" "));
			String gcvs = gc.getVersionInfo().substring(gc.getVersionInfo().indexOf("=") + 1);
			double gcvr = gc.getVRam();
		}
	}

}
