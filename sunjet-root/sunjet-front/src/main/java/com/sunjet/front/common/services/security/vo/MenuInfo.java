package com.sunjet.front.common.services.security.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuInfo {
	private String name;
	private String url;
	private Integer ordinary;
	private String parentMenu;
	private List<MenuInfo> sonMenus;
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
	public List<MenuInfo> getSonMenus() {
		if (null == sonMenus) {
			sonMenus = new ArrayList<MenuInfo>();
		}
		return sonMenus;
	}
	public void setSonMenus(List<MenuInfo> sonMenus) {
		this.sonMenus = sonMenus;
	}
	
	
}
