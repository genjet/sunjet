package com.sunjet.common.entity.enumeration;

public enum LeaveStatusEnum {

	F("初審中"), A("核可"), C("系統退件"), R("退件"), T("轉簽"), U("簽核中"), V("審核中");

	private String value;

	LeaveStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
