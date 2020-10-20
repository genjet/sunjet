package com.sunjet.front.leave.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.payload.response.ApiResponse;
import com.sunjet.front.common.security.vo.SecurityUserDetails;
import com.sunjet.front.job.service.JobService;
import com.sunjet.front.leave.service.LeaveService;
import com.sunjet.front.leave.vo.LeaveFormVo;
import com.sunjet.front.leave.vo.LeaveVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private JobService jobService;
	
	
	@PostMapping("/leave")
	public ApiResponse getLeaves(@RequestBody LeaveFormVo formVo) {
		SecurityUserDetails userInfo = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("======================> "+userInfo.getAccount());
		if(null != formVo){
			log.info(formVo.toString());
		}
		List<LeaveVo> rtnVOs = leaveService.queryLeave(formVo, userInfo.getAccount());
		return ApiResponse.ok("", rtnVOs);
	}
	

	@RequestMapping("/createCalendar")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String createCalendar() {
		LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
		LocalDate end = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
		jobService.calculateCalendar(start, end);
		return "leave/leave2";
	}

//	@RequestMapping("/leave")
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	public String tables(Model model) {
//		UserDetailsImpl userInfo = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		SjUser sjUser = sjUserRepository.findByAccount(userInfo.getUsername());
//
//		model.addAttribute("sjLeaves", sjUser.getSjLeave());
//		return "leave/leave";
//	}

//	@RequestMapping("/leave2")
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	public String tables2(Model model) {
//		// UserInfo userInfo = (UserInfo)
//		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		// SjUser sjUser =
//		// sjUserRepository.findByAccount(userInfo.getUsername());
//
//		model.addAttribute("leaveVO", new LeaveVO());
//		return "leave/leave2";
//	}

	@PostMapping("/getLeaveableDays")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String getLeaveableDays(Model model, @RequestParam Map<String, String> data) {
		SecurityUserDetails userInfo = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LeaveTypeEnum leaveType = LeaveTypeEnum.valueOf(data.get("leaveType"));
		double count = leaveService.countLeaveableDays(userInfo, leaveType);
		model.addAttribute("leaveableDay", count);
		return "leave/leave2::leaveableDayDiv";
	}

	@PostMapping("/getLeaveDays")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String getLeaveDays(Model model, @RequestParam Map<String, String> data) {
		// UserInfo userInfo = (UserInfo)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime startDate = LocalDateTime.parse(data.get("startDate"), formatter);
		LocalDateTime endDate = LocalDateTime.parse(data.get("endDate"), formatter);
		Map<String, Integer> map = leaveService.countLeaveaDays(startDate, endDate);
		model.addAttribute("day", map.get("day"));
		model.addAttribute("leaveHours", map.get("hour"));
		return "leave/leave2::leaveDayHourDiv";
	}

	@PostMapping("/addLeave")
	public ApiResponse addLeave(@RequestBody LeaveVo leaveVo) {
		try {
			LeaveVo rtnVO = leaveService.addLeave(leaveVo);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}
	
//	@PostMapping("/addLeave")
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	public String addLeave(@Valid LeaveVo leaveVO, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "leave/leave";
//		}
//
//		return "redirect:leave2";
//	}

//	public String addUser(@Valid LeaveVO userVO, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			// model.addAttribute("userVOs", getUserVo());
//			return "management/management";
//		}
//		// SjUser user = new SjUser();
//		// user.setAccount(userVO.getAccount());
//		// user.setName(userVO.getName());
//		// user.setPwd(userVO.getPsw());
//		// sjUserRepository.save(user);
//		// model.addAttribute("users", getUserVo());
//		return "redirect:leave";
//	}

}