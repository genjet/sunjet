package com.sunjet.common.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;

@Repository
public interface SjLeaveRepository extends JpaRepository<SjLeave, String> {
	public List<SjLeave> findBySjUserAndLeaveTypeAndExpire(SjUser sjUser, LeaveTypeEnum leaveType, Boolean expire);

	@Query("SELECT c FROM SjLeave c WHERE expire = 'Y' and sjUser = :sjUser and (:leaveType is null or c.leaveType = :leaveType) and (:startDate is null or c.startDatetime = :startDate) and (:endDate is null or c.endDatetime = :endDate) ")
	public Optional<List<SjLeave>> findSjLeaveByFrom(@Param("sjUser") SjUser sjUser, @Param("leaveType") LeaveTypeEnum leaveType,
		 @Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate);

}