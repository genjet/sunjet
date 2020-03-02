package com.sunjet.front.leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.services.security.vo.UserInfo;

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
		return "/leave/leave";
	}

}
