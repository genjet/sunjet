package com.sunjet.front.leave.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.security.vo.SecurityUserDetails;
import com.sunjet.front.leave.vo.LeaveFormVO;
import com.sunjet.front.leave.vo.LeaveVO;

public interface LeaveService {
	public List<LeaveVO> queryLeave(LeaveFormVO leaveForm, String account);
	
	double countLeaveableDays(SecurityUserDetails userInfo, LeaveTypeEnum leaveType);

	public Map<String, Integer> countLeaveaDays(LocalDateTime startDate, LocalDateTime endDate);

	public SjLeave createLeave(LeaveVO leaveVO);
}