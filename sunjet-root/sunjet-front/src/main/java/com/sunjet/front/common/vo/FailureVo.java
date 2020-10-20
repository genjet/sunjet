package com.sunjet.front.common.vo;

import lombok.Data;

@Data
public class FailureVo {
	private String code;
	private String message;
	
	public FailureVo(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	public FailureVo(String message){
		this.message = message;
	}
	
}
