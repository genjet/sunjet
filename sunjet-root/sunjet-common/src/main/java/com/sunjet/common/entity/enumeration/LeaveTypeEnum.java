package com.sunjet.common.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LeaveTypeEnum {

	P("事假", "1"), T("病假", "2"), B("特休假", "3"), M("補休假", "4"), F("特別假", "5"), R("公假", "6"), Z("婚假", "7"), Q("產假",
			"8"), W("陪產假", "9"), O("喪假", "10"), S("生理假", "11"), N("生日假", "12");
	//
	@Getter
	private String code;
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
