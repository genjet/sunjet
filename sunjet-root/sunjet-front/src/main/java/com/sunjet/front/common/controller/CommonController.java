package com.sunjet.front.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	@RequestMapping("/login")
	public String login() {
		System.out.println("iiiiiiiiiiiiiiiiii");
		return "index/login";
	}

	@GetMapping("/logout")
	public String logout() {
		System.out.println("55555ddddddddddddd");
		return "index/logout";
	}

	@RequestMapping("/403")
	public String no403() {
		return "index/403";
	}

	@RequestMapping("/404")
	public String no404() {
		return "index/404";
	}

	@RequestMapping("/500")
	public String no500() {
		return "index/500";
	}
}
