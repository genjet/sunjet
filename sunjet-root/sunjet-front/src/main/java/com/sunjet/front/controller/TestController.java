package com.sunjet.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.SjMenu;
import com.sunjet.front.services.TestService;

@Controller
// @RequestMapping("/user")
public class TestController {
	@Autowired
	private TestService testService;

	@GetMapping("/")
	public String index(Model model) {
		List<SjMenu> sjMenus = testService.getAllMenu();
		model.addAttribute("sjMenus", sjMenus);
		return "Content1";
	}

	@GetMapping("/index")
	public String index1(Model model) {
//		List<SjMenu> sjMenus = testService.getAllMenu();
//		model.addAttribute("sjMenus", sjMenus);
		return "index/index";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<SjLeave> sjLeaves = testService.getAllLeave();
		model.addAttribute("sjLeave", sjLeaves);
		return "list";
	}


	@RequestMapping("/login")
	public String login() {
		System.out.println("iiiiiiiiiiiiiiiiii");
		return "index/login";
	}

	@RequestMapping("/tables")
	public String tables(Model model) {
		List<SjLeave> sjLeaves = testService.getAllLeave();
		model.addAttribute("sjLeave", sjLeaves);
		System.out.println("table");
		return "tables";
	}

	@GetMapping("/logout")
	public String logout() {
		System.out.println("55555ddddddddddddd");
		return "index/logout";
	}

	@GetMapping("/charts")
	public String charts() {
		System.out.println("55555ddddddddddddd");
		return "charts";
	}

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public String test() {
	// try {
	// // testService.loadUserByUsername("username");
	// } catch (Exception e) {
	// System.out.println(e.toString());
	// }
	// return "index";
	// }
	//
	// @RequestMapping(value = "/hello", method = RequestMethod.POST)
	// public String sayHello(@RequestParam("name") String name, Model model) {
	// model.addAttribute("name", name);
	// return "hello";
	// }
	//
	// @RequestMapping("/login-error")
	// public String loginError() {
	// return "login-error";
	//
	// }
	//
	// @RequestMapping("/thymeleaf")
	// public String helloThymeleaf(Model model){
	// model.addAttribute("hello","hello Thymeleaf！");
	// return "NewFile";
	// }
}
