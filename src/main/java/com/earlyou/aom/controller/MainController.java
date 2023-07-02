package com.earlyou.aom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model m) {
		m.addAttribute("main", "dashboard/main");
		return "index";
	}
	
	@GetMapping("/sysspec")
	public String sysspec(Model m) {
		m.addAttribute("main", "sysspec/sysspec");
		return "index";
	}
}
