package com.sunjet.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.sunjet.front.common.services.TestService;

@ControllerAdvice
public class GlobalController {
	@Autowired
	private TestService testService;

//	@ModelAttribute(name="categories")
//	public List<SjMenu> initMenu(){
//		return testService.getAllMenu();
//	}
}
