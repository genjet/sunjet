package com.sunjet.front.leave.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.services.security.vo.UserInfo;
import com.sunjet.front.leave.service.LeaveService;
import com.sunjet.front.leave.vo.LeaveVO;
import com.sunjet.front.management.vo.UserVO;

@Controller
public class LeaveController {
	@Autowired
	private SjUserRepository sjUserRepository;
	@Autowired
	private LeaveService leaveService;

	@RequestMapping("/leave")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String tables(Model model) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SjUser sjUser = sjUserRepository.findByAccount(userInfo.getUsername());

		model.addAttribute("sjLeaves", sjUser.getSjLeave());
		return "leave/leave";
	}

	@RequestMapping("/leave2")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String tables2(Model model) {
		// UserInfo userInfo = (UserInfo)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// SjUser sjUser =
		// sjUserRepository.findByAccount(userInfo.getUsername());

		model.addAttribute("leaveVO", new LeaveVO());
		return "leave/leave2";
	}

	@PostMapping("/getLeaveableDays")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String getLeaveableDays(Model model, @RequestParam Map<String, String> data) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LeaveTypeEnum leaveType = LeaveTypeEnum.valueOf(data.get("leaveType"));
		int count = leaveService.countLeaveableDays(userInfo, leaveType);
		model.addAttribute("leaveableDay", count);
		return "leave/leave2::leaveableDayDiv";
	}

	@GetMapping("/leave3")
	@PreAuthorize("hasAnyRole('ADMIN')")
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
