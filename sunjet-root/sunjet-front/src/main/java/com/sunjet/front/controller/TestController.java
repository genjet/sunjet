package com.sunjet.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/user")
public class TestController {
//	@Autowired
//	private TestService testService;
	
	 @GetMapping("/")
	    public String index() {
	        return "index/index";
	    }

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

//	@RequestMapping(value = "/index", method = RequestMethod.GET)
//	public String test() {
//		try {
//			// testService.loadUserByUsername("username");
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return "index";
//	}
//
//	@RequestMapping(value = "/hello", method = RequestMethod.POST)
//	public String sayHello(@RequestParam("name") String name, Model model) {
//		model.addAttribute("name", name);
//		return "hello";
//	}
//
//	@RequestMapping("/login-error")
//	public String loginError() {
//		return "login-error";
//
//	}
//	
//	@RequestMapping("/thymeleaf")
//	public String helloThymeleaf(Model model){
//		model.addAttribute("hello","hello Thymeleaf！");
//		return "NewFile";
//	}
}
