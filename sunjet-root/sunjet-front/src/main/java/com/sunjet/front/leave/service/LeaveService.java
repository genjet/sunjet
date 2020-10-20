package com.sunjet.front.leave.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.security.vo.SecurityUserDetails;
import com.sunjet.front.leave.vo.LeaveFormVo;
import com.sunjet.front.leave.vo.LeaveVo;

public interface LeaveService {
	public List<LeaveVo> queryLeave(LeaveFormVo leaveForm, String account);
	
	double countLeaveableDays(SecurityUserDetails userInfo, LeaveTypeEnum leaveType);

	public Map<String, Integer> countLeaveaDays(LocalDateTime startDate, LocalDateTime endDate);

	public LeaveVo addLeave(LeaveVo leaveVO);
}