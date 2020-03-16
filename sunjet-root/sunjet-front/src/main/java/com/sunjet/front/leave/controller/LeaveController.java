package com.sunjet.front.leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.services.security.vo.UserInfo;
import com.sunjet.front.management.vo.UserVO;

@Controller
public class LeaveController {
	@Autowired
	private SjUserRepository sjUserRepository;

	@RequestMapping("/leave")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String tables(Model model) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SjUser sjUser = sjUserRepository.findByAccount(userInfo.getUsername());

		model.addAttribute("sjLeaves", sjUser.getSjLeave());
		return "leave/leave";
	}

	@RequestMapping("/leave3")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	public String tables2(Model model) {
		// UserInfo userInfo = (UserInfo)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// SjUser sjUser =
		// sjUserRepository.findByAccount(userInfo.getUsername());

		model.addAttribute("userVO", new UserVO());
		return "leave/leave2";
	}

	@GetMapping("/leave2")
	public ModelAndView addSong(Model model) {

		ModelAndView modelAndView = new ModelAndView();

		UserVO userVO = new UserVO();
		modelAndView.addObject("userVO", userVO);
		modelAndView.addObject("hintMessage", "初始化成功！");
		modelAndView.setViewName("/leave/leave2");
		// model.addAttribute("userVO", new UserVO());
		return modelAndView;
	}
}
