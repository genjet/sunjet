package com.sunjet.front.leave.vo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.sunjet.common.entity.enumeration.LeaveStatusEnum;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;

public class LeaveVO {

	private long id;

	private String userName;
	private String userAccount;

	private String dep;
	private LeaveTypeEnum leaveType;
	private LeaveStatusEnum leaveStatus;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime startDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime endDate;
	private double leaveHours;

	public LeaveVO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public LeaveTypeEnum getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveTypeEnum leaveType) {
		this.leaveType = leaveType;
	}

	public LeaveStatusEnum getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatusEnum leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDatetime) {
		this.startDate = startDatetime;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDatetime) {
		this.endDate = endDatetime;
	}

	public double getLeaveHours() {
		return leaveHours;
	}

	public void setLeaveHours(double leaveHours) {
		this.leaveHours = leaveHours;
	}

}