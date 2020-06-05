package com.sunjet.front.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sunjet.front.leave.vo.LeaveVO;

public class ToolUtil {

	public static String addOne4Str(String str) {

		if (str != null) {
			int fIndex = -1;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c >= '0' && c <= '9') {
					fIndex = i;
					break;
				}
			}
			if (fIndex == -1) {
				return str;
			}
			int num = Integer.parseInt(str.substring(fIndex, str.length())) + 1;
			String zeroStr = addZero4Left(str.substring(fIndex, str.length()), String.valueOf(num));
			String preStr = str.substring(0, fIndex);
			String wholeStr = preStr + zeroStr + num;
			return wholeStr;
		}
		return str;
	}

	public static String addZero4Left(String sourceStr, String numberStr) {
		StringBuffer sb = new StringBuffer();
		int len = sourceStr.length() - numberStr.length();
		for (int i = 0; i < len; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	private static final char UNDERLINE = '_';

	public static void main(String[] args) throws Exception {
		LeaveVO data = new LeaveVO(); // or List<SomeClass> data; etc.
		Class cs = data.getClass();
		String className = cs.getSimpleName();

		List<String> fieldNames = getFieldNamesForClass(cs);

		StringBuffer sb = new StringBuffer();
		fieldNames.forEach(it -> {
			System.out.println("{");
			System.out.println("  prop: \"" + it + "\",");
			System.out.println("  label: this.$I18N(`" + it + "`),");
			System.out.println("  operate: {}");
			System.out.println("},");
		});

	}

	private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}

	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = i != 0 ? Character.toLowerCase(param.charAt(i)) : Character.toUpperCase(param.charAt(i));
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
