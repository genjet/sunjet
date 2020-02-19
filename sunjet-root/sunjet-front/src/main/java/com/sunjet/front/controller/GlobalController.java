package com.sunjet.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sunjet.common.entity.SjMenu;
import com.sunjet.front.services.TestService;

@ControllerAdvice
public class GlobalController {
	@Autowired
	private TestService testService;

//	@ModelAttribute(name="categories")
//	public List<SjMenu> initMenu(){
//		return testService.getAllMenu();
//	}
}
