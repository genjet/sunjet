package com.sunjet.front.management.vo;

import lombok.Data;

@Data
public class AuthorityVo {
	private String oid;
	private String authorityCode;
	private String authorityName;

	public AuthorityVo() {
	}

	public AuthorityVo(String authorityCode, String authorityName) {
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
	}
}
