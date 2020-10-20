package com.sunjet.front.leave.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sunjet.common.entity.enumeration.LeaveTypeEnum;

import lombok.Data;

@Data
public class LeaveFormVO {


	private List<LocalDateTime> rangeDate = new ArrayList<LocalDateTime>();
	private String taxId;

	private LeaveTypeEnum leaveType;

	

}