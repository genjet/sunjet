package com.sunjet.front.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.front.services.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;

//	@RequestMapping("/test")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		try {
			testService.insertUser();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "test";
	}
	
	 @GetMapping
     public Map hello() {
         Map map = new HashMap();
         map.put("say1", "hello1");
         return map;
     }
}
