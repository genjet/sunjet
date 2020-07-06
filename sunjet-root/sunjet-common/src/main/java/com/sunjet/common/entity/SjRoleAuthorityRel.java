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
@Table(name = "sj_role_authority_rel")
//@Data
public class SjRoleAuthorityRel extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_role")
	private SjRole sjRole;//角色

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_authority")
	private SjAuthority sjAuthority;//權限

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public SjRole getSjRole() {
		return sjRole;
	}

	public void setSjRole(SjRole sjRole) {
		this.sjRole = sjRole;
	}

	public SjAuthority getSjAuthority() {
		return sjAuthority;
	}

	public void setSjAuthority(SjAuthority sjAuthority) {
		this.sjAuthority = sjAuthority;
	}
	
	

}
