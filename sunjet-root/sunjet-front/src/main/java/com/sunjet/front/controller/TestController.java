package com.sunjet.front.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.front.common.services.TestService;
import com.sunjet.front.job.service.JobService;

@Controller
// @RequestMapping("/user")
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private JobService jobService;

	@GetMapping("/")
	public String index(Model model) {
		// List<SjMenu> sjMenus = testService.getAllMenu();
		// model.addAttribute("sjMenus", sjMenus);
		return "Content1";
	}

	@GetMapping("/index")
	public String index1(Model model) {
		// List<SjMenu> sjMenus = testService.getAllMenu();
		// model.addAttribute("sjMenus", sjMenus);
		return "index/index";
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('CREATE')")
	public String list(Model model) {
		List<SjLeave> sjLeaves = testService.getAllLeave();
		model.addAttribute("sjLeave", sjLeaves);
		return "list";
	}

	@RequestMapping("/alculateCalendar")
	public String tables(Model model) {
		LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
		LocalDate end = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
		jobService.calculateCalendar(start, end);
		return "/list";
	}

	// @GetMapping("/charts")
	// public String charts() {
	// System.out.println("55555ddddddddddddd");
	// return "charts";
	// }

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
