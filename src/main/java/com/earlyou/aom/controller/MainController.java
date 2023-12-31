package com.earlyou.aom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@Controller
@Slf4j
public class MainController {

	@Autowired
	OsinfoBiz osbiz;
	@Autowired
	MbBiz mbbiz;
	@Autowired
	CpuBiz cpubiz;
	@Autowired
	RamBiz rambiz;
	@Autowired
	FilestoreBiz fsbiz;
	@Autowired
	VgaBiz vgabiz;

	SystemInfo si = new SystemInfo();
	HardwareAbstractionLayer hal = si.getHardware();
	GlobalMemory memory = hal.getMemory();

	@GetMapping("/")
	public String main(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "dashboard/main");
		return "index";
	}

	@GetMapping("/specification")
	public String specification(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "sysspec/specification");

		OsinfoVO os = null;
		MbVO mb = null;
		CpuVO cpu = null;
		List<RamVO> ramlist = null;
		List<FilestoreVO> fslist = null;
		List<VgaVO> vgalist = null;
		try {
			os = osbiz.get().get(0);
			mb = mbbiz.get().get(0);
			cpu = cpubiz.get().get(0);
			ramlist = rambiz.get();
			fslist = fsbiz.get();
			vgalist = vgabiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("os", os);
		m.addAttribute("mb", mb);
		m.addAttribute("cpu", cpu);
		m.addAttribute("ramlist", ramlist);
		m.addAttribute("fslist", fslist);
		m.addAttribute("vgalist", vgalist);
		return "index";
	}

	@GetMapping("/performance")
	public String performance(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "sysspec/performance");

		double totmem = memory.getTotal();
		totmem = Math.round(totmem / (1024 * 1024 * 1024) * 100) / 100.0;
		m.addAttribute("totmem", totmem);

		List<FilestoreVO> fslist = null;
		try {
			fslist = fsbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("fslist", fslist);

		return "index";
	}
	
	@GetMapping("/gmap")
	public String gmap(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "maps/gmap");
		return "index";
	}
	
	@GetMapping("/kmap")
	public String kmap(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "maps/kmap");
		return "index";
	}
	
	@GetMapping("/nmap")
	public String nmap(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "maps/nmap");
		return "index";
	}
	
	@GetMapping("/ads")
	public String ads(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "dashboard/ads");
		return "index";
	}
}
