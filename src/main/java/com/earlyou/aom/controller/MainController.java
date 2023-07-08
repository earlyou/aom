package com.earlyou.aom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "dashboard/main");
		return "index";
	}
	
	@GetMapping("/specification")
	public String specification(Model m) {
		m.addAttribute("main", "sysspec/specification");
		return "index";
	}
	
	@GetMapping("/performance")
	public String performance(Model m) {
		m.addAttribute("main", "sysspec/performance");
		return "index";
	}
}
