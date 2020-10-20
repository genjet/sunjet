package com.sunjet.front.common.vo;

import lombok.Data;

@Data
public class BaseOptionVo {
	private String label;
	private String value;
	
	public BaseOptionVo(String label, String value){
		this.label = label;
		this.value = value;
	}
}
