package com.earlyou.aom;

import java.time.Instant;
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

		// Osinfo
		log.info("Updating OS informaion");
		osbiz.removeall();
		String osinfo = os.toString();
		String booted = String.valueOf(Instant.ofEpochSecond(os.getSystemBootTime()));
		booted = booted.replace("T", " ").replace("Z", "");
		OsinfoVO newos = new OsinfoVO(osinfo, booted);
		osbiz.register(newos);

		// Motherboard
		log.info("Updating Motherboard informaion");
		mbbiz.removeall();
		String mbsn = mb.getSerialNumber();
		String mbmfr = mb.getManufacturer();
		String mbmd = mb.getModel();
		String mbvs = mb.getVersion();
		if (mbvs.isBlank()) {
			mbvs = com.getFirmware().getVersion();
		}
		MbVO newmb = new MbVO(mbsn, mbmfr, mbmd, mbvs);
		mbbiz.register(newmb);

		// CPU
		log.info("Updating CPU informaion");
		cpubiz.removeall();
		String cname = cpu.getProcessorIdentifier().getName();
		String carch = cpu.getProcessorIdentifier().getMicroarchitecture();
		int cpc = cpu.getPhysicalPackageCount();
		int cppc = cpu.getPhysicalProcessorCount();
		int clpc = cpu.getLogicalProcessorCount();
		CpuVO newcpu = new CpuVO(cname, carch, cpc, cppc, clpc);
		cpubiz.register(newcpu);

		// Memory
		log.info("Updating Memory informaion");
		rambiz.removeall();
		List<PhysicalMemory> mem = memory.getPhysicalMemory();
		for (PhysicalMemory pm : mem) {
			String mbank = pm.getBankLabel();
			double mcapa = pm.getCapacity();
			double mclock = pm.getClockSpeed();
			String mmfr = pm.getManufacturer();
			String mtype = pm.getMemoryType();
			RamVO newram = new RamVO(mbank, mcapa, mclock, mmfr, mtype);
			rambiz.register(newram);
		}

		// FileStore
		log.info("Updating FileStore informaion");
		fsbiz.removeall();
		for (OSFileStore fs : fileSystem.getFileStores()) {
			String fsmnt = fs.getMount();
			double fstot = fs.getTotalSpace();
			String fstype = fs.getType();
			FilestoreVO newfs = new FilestoreVO(fsmnt, fstot, fstype);
			fsbiz.register(newfs);
		}

		// Graphic Cards
		log.info("Updating Graphic Cards informaion");
		vgabiz.removeall();
		List<GraphicsCard> vga = hal.getGraphicsCards();
		for (GraphicsCard gc : vga) {
			String gcid = gc.getDeviceId();
			String gcnm = gc.getName();
			String gcvd = gc.getVendor().substring(0, gc.getVendor().indexOf(" "));
			String gcvs = gc.getVersionInfo().substring(gc.getVersionInfo().indexOf("=") + 1);
			double gcvr = gc.getVRam();
			VgaVO vgavo = new VgaVO(gcid, gcnm, gcvd, gcvs, gcvr);
			vgabiz.register(vgavo);
		}
	}

}
