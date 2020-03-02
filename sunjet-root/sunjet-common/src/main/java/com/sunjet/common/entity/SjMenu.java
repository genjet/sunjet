package com.sunjet.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sj_menu")
public class SjMenu extends GenericEntity{
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	@Column(name = "name")
	private String name;
	@Column(name = "url")
	private String url;
	@Column(name = "ordinary")
	private Integer ordinary;
	@Column(name = "parent_menu")
	private String parentMenu;
	
	

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrdinary() {
		return ordinary;
	}

	public void setOrdinary(Integer ordinary) {
		this.ordinary = ordinary;
	}

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}



	

}
