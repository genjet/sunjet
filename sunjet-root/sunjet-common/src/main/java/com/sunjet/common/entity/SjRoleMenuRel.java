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
@Table(name = "sj_role_menu_rel")
public class SjRoleMenuRel extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_menu")
	private SjMenu sjMenu;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_role")
	private SjRole sjRole;// 角色

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public SjMenu getSjMenu() {
		return sjMenu;
	}

	public void setSjMenu(SjMenu sjMenu) {
		this.sjMenu = sjMenu;
	}

	public SjRole getSjRole() {
		return sjRole;
	}

	public void setSjRole(SjRole sjRole) {
		this.sjRole = sjRole;
	}

}
