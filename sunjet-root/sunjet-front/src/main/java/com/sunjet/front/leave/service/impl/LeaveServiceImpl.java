package com.sunjet.front.leave.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjLeaveRepository;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.enumeration.LeaveStatusEnum;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.services.security.vo.UserInfo;
import com.sunjet.front.leave.service.LeaveService;
import com.sunjet.front.util.LeaveUtil;

@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private SjLeaveRepository sjLeaveRepository;
	@Autowired
	private SjUserRepository sjUserRepository;

	@Override
	public int countLeaveableDays(UserInfo userInfo, LeaveTypeEnum leaveType) {
		int totalHours = 0;
		SjUser sjUser = sjUserRepository.findByName(userInfo.getName());
		List<SjLeave> leaves = sjLeaveRepository.findBySjUserAndLeaveTypeAndExpire(sjUser, leaveType, Boolean.FALSE);
		if (CollectionUtils.isNotEmpty(leaves)) {
			for (SjLeave sjLeave : leaves) {
				if (!LeaveStatusEnum.R.equals(sjLeave.getLeaveStatus())) {
					totalHours += sjLeave.getLeaveHours();
				}
			}
		}
		LocalDate offDate = sjUser.getOffdate();
		Map<String, Object> map = LeaveUtil.countNewAN(offDate);
		System.out.println(map.get("hours"));
		switch (leaveType) {
		case SPECIAL:

			break;

		default:
			break;
		}
		return totalHours;
	}

}
