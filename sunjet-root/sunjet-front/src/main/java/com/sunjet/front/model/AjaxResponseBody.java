package com.sunjet.front.model;

import java.util.List;

import com.sunjet.front.common.services.security.vo.UserInfo;

public class AjaxResponseBody {
	String msg;

	String code;

	List<UserInfo> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserInfo> getResult() {
		return result;
	}

	public void setResult(List<UserInfo> result) {
		this.result = result;
	}

}
