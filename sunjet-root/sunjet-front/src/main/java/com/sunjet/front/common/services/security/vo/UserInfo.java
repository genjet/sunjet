package com.sunjet.front.common.services.security.vo;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserInfo extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7908268797043419113L;
	private Set<MenuInfo> menus;
	private String name;
	private String dep;

	public UserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public Set<MenuInfo> getMenus() {
		return menus;
	}

	public void setMenus(Set<MenuInfo> menus) {
		this.menus = menus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

}
