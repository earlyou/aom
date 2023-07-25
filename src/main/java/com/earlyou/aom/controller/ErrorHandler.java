package com.earlyou.aom.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandler implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model m) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		m.addAttribute("sidebar", "sidebar");

		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				m.addAttribute("main", "error/error-404");
				return "index";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				m.addAttribute("main", "error/error-403");
				return "index";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				m.addAttribute("main", "error/error-500");
				return "index";
			}
		}

		m.addAttribute("main", "error/error-404");
		return "index";
	}

}
