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

@Entity
@Table(name = "sj_user")
public class SjUser extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	@Column(name = "name")
	private String name;
	@Column(name = "account")
	private String account;
	@Column(name = "pwd")
	private String pwd;
	@Column(name = "enabled")
	@Type(type = "yes_no")
	private boolean enabled = Boolean.TRUE;

	@Column(name = "offdate")
	private LocalDate offdate = LocalDate.now();

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_dep")
	private SjDep sjDep;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sjUser")
	private List<SjLeave> sjLeave;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "sjUser", orphanRemoval = true)
	private List<SjUserRoleRel> sjUserRoleRels;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "sjUserLeaveDetail", referencedColumnName = "oid")
	// private SjUserLeaveDetail sjUserLeaveDetail;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public SjDep getSjDep() {
		return sjDep;
	}

	public void setSjDep(SjDep sjDep) {
		this.sjDep = sjDep;
	}

	public List<SjLeave> getSjLeave() {
		return sjLeave;
	}

	public void setSjLeave(List<SjLeave> sjLeave) {
		this.sjLeave = sjLeave;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<SjUserRoleRel> getSjUserRoleRels() {
		return sjUserRoleRels;
	}

	public void setSjUserRoleRels(List<SjUserRoleRel> sjUserRoleRels) {
		this.sjUserRoleRels = sjUserRoleRels;
	}

	public LocalDate getOffdate() {
		return offdate;
	}

	public void setOffdate(LocalDate offdate) {
		this.offdate = offdate;
	}

	// public SjUserLeaveDetail getSjUserLeaveDetail() {
	// return sjUserLeaveDetail;
	// }
	//
	// public void setSjUserLeaveDetail(SjUserLeaveDetail sjUserLeaveDetail) {
	// this.sjUserLeaveDetail = sjUserLeaveDetail;
	// }

}
