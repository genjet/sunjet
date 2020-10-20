package com.sunjet.front.common.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OptionVo extends BaseOptionVo{

	private List<BaseOptionVo> children = new ArrayList<BaseOptionVo>();
	
	public OptionVo(String label, String value) {
		super(label, value);
	}
}
