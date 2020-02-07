package com.sunjet.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunjet.front.services.TestService;

@Controller
// @RequestMapping("/user")
public class TestController {
	@Autowired
	private TestService testService;

	@RequestMapping("/login")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String test() {
		try {
			// testService.loadUserByUsername("username");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "index";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public String sayHello(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping("/login-error")
	public String loginError() {
		return "login-error";

	}
}
