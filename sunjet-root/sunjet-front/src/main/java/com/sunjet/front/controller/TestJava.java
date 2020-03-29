package com.sunjet.front.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

import com.sunjet.front.util.LeaveUtil;

public class TestJava {

	public static void main(String[] args) {
		// String authorityString = "read,ROLE_USER";
		// for (String string :
		// StringUtils.tokenizeToStringArray(authorityString, ",")) {
		// System.out.println(string);
		//
		// }
		LocalDate today = LocalDate.now();
		Duration duration = Duration.between((today.minusYears(1)).atStartOfDay(), today.atStartOfDay());
		long d = duration.toDays();
		System.out.println(d);
		Map<String, Object> map = LeaveUtil.countNewAN(LocalDate.of(2019, 9, 30));
		System.out.println(map.get("hours"));
	}

}
