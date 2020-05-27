package com.sunjet.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;

@Repository
public interface SjLeaveRepository extends JpaRepository<SjLeave, String> {
	public List<SjLeave> findBySjUserAndLeaveTypeAndExpire(SjUser sjUser, LeaveTypeEnum leaveType, Boolean expire);

}