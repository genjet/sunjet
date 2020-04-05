package com.sunjet.front.controller;

import java.math.BigDecimal;

public class TestJava {

	public static void main(String[] args) {
		// String authorityString = "read,ROLE_USER";
		// for (String string :
		// StringUtils.tokenizeToStringArray(authorityString, ",")) {
		// System.out.println(string);
		//
		// }
		// LocalDate today = LocalDate.now();
		// Duration duration =
		// Duration.between((today.minusYears(1)).atStartOfDay(),
		// today.atStartOfDay());
		// long d = duration.toDays();
		// System.out.println(d);
		int iii = (new BigDecimal(23).divide(new BigDecimal(8), 0, BigDecimal.ROUND_DOWN)).intValue();
		System.out.println(iii);
	}

}
