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

@Controller
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

		double totmem = 0.0;
		try {
			List<RamVO> ramlist = rambiz.get();
			for (RamVO ramvo : ramlist) {
				totmem = totmem + ramvo.getMcapa();
			}
			totmem = Math.round(totmem/(1024*1024*1024)*100)/100.0;
			m.addAttribute("totmem", totmem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "index";
	}
}
