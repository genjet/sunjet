package com.sunjet.front.leave.vo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.sunjet.common.entity.enumeration.LeaveStatusEnum;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;

import lombok.Data;

@Data
public class LeaveVo {

	private String id;

	private String name;
	private String dep;
	private LeaveTypeEnum leaveType;
	private LeaveStatusEnum leaveStatus;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime startDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime endDate;
	private double leaveHours;

	

}