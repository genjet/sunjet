package com.sunjet.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sj_user_role_rel")
//@Data
public class SjUserRoleRel extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_user")
	private SjUser sjUser;// 使用者

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_role")
	private SjRole sjRole;// 角色

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public SjUser getSjUser() {
		return sjUser;
	}

	public void setSjUser(SjUser sjUser) {
		this.sjUser = sjUser;
	}

	public SjRole getSjRole() {
		return sjRole;
	}

	public void setSjRole(SjRole sjRole) {
		this.sjRole = sjRole;
	}
	
	

}
