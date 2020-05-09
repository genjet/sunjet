package com.sunjet.front.leave.service;

import java.time.LocalDateTime;
import java.util.Map;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.services.security.vo.UserInfo;
import com.sunjet.front.leave.vo.LeaveVO;

public interface LeaveService {
	double countLeaveableDays(UserInfo userInfo, LeaveTypeEnum leaveType);

	public Map<String, Integer> countLeaveaDays(LocalDateTime startDate, LocalDateTime endDate);

	public SjLeave createLeave(LeaveVO leaveVO);
}
