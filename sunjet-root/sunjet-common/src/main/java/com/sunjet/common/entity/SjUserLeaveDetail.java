package com.sunjet.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sj_user_leave_detail")
public class SjUserLeaveDetail extends GenericEntity {

	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	/**
	 * 剩餘天數。
	 */
	@Column(name = "remain_Days")
	private int remainDays;

	@Column(name = "remain_Hours")
	private int remainHours;

	// @OneToOne(mappedBy = "sjUser")
	// private SjUser sjUser;

}
