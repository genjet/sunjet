package com.sunjet.front.leave.service;

import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.services.security.vo.UserInfo;

public interface LeaveService {
	int countLeaveableDays(UserInfo userInfo, LeaveTypeEnum leaveType);
}
