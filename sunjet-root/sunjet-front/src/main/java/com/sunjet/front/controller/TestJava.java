package com.sunjet.front.controller;

import org.springframework.util.StringUtils;

public class TestJava {

	public static void main(String[] args) {
		String authorityString = "read,ROLE_USER";
		for (String string : StringUtils.tokenizeToStringArray(authorityString, ",")) {
			System.out.println(string);

		}
	}

}
