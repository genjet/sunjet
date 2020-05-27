package com.sunjet.common.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.sunjet.common.entity.enumeration.LeaveStatusEnum;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;

import lombok.Data;

@Entity
@Table(name = "sj_leave")
@Data
public class SjLeave extends GenericEntity {

	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_user")
	private SjUser sjUser;

	@Column(name = "dep")
	private String dep;
	@Column(name = "leave_type")
	@Enumerated(EnumType.STRING)
	private LeaveTypeEnum leaveType;
	@Column(name = "leave_status")
	@Enumerated(EnumType.STRING)
	private LeaveStatusEnum leaveStatus;
	@Column(name = "start_datetime")
	private LocalDateTime startDatetime;
	@Column(name = "end_datetime")
	private LocalDateTime endDatetime;
	/**
	 * 原因。
	 */
	@Column(name = "reason")
	private String reason;
	@Column(name = "leave_hours")
	private double leaveHours;

	@Column(name = "expire")
	@Type(type = "yes_no")
	private boolean expire = Boolean.FALSE;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_apply")
	private SjApply sjApply;


}
