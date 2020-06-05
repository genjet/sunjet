package com.sunjet.common.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Table(name = "sj_user")
@Data
public class SjUser extends GenericEntity{
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	@Column(name = "emp_Id")
	private String empId;
	@Column(name = "name")
	private String name;
	@Column(name = "account")
	private String account;
	@Column(name = "pwd")
	private String pwd;
	@Column(name = "enabled")
	@Type(type = "yes_no")
	private Boolean enabled = Boolean.TRUE;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "nick_name")
	private String nickName;
	
	@Column(name = "arrival_day")
	private LocalDate arrivalDay;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_dep")
	private SjDep sjDep;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sjUser")
	private List<SjLeave> sjLeave;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "sjUser", orphanRemoval = true)
	private List<SjUserRoleRel> sjUserRoleRels;
	
}
