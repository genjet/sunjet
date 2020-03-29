package com.sunjet.common.entity.enumeration;

public enum LeaveTypeEnum {

	// A("事假", "1"), T("病假", "2"), B("特休假", "3"),
	// M("補休假", "4"), F("特別假", "5"), R("公假", "6"),
	// Z("婚假", "7"), Q("產假", "8"), W("陪產假", "9"),
	// O("喪假", "10"), S("生理假", "11"), N("生日假", "12");

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

	private String name;

	LeaveTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
