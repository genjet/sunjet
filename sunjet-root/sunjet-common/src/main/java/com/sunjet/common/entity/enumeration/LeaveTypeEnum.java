package com.sunjet.common.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LeaveTypeEnum {

	BUSINESS("公假"),

	PERSONAL("事假"),

	SICK("一般病假"),

	BUSINESS_SICK("公傷病假"),

	MRRRIAGE("婚假"),

	MATERNITY("產假"),

	PETERNITY("陪產假"),

	BEREAVEMENT("喪假"),

	SPECIAL("特休假"),

	COMPLEMENT("補休假"),

	PHYSIOLOGICAL("生理假"),

	BIRTHDAY("生日假"),

	SPECIAL_VACATION("特別假");
	
	@Getter
	private String value;
	//
	// LeaveTypeEnum(String code, String value) {
	// this.code = code;
	// this.value = value;
	// }
	//
	// public String getCode() {
	// return this.code;
	// }
	//
	// public String getValue() {
	// return this.value;
	// }

	// A("核可"), C("系統退件"), F("初審中"), R("退件"), T("轉簽"), U("簽核中"), V("審核中");

	// private String value;
	//
	// LeaveTypeEnum(String value) {
	// this.value = value;
	// }
	//
	// public String getValue() {
	// return value;
	// }
}
