package com.pkproject.internetcourse.application.utils;

import java.util.regex.Pattern;

public class AssertUtils {

	public static boolean isNotEmpty(String argument) {
		return !isEmpty(argument);
	}

	public static boolean isEmpty(String argument) {
		return argument == null ? true : argument.isEmpty();
	}

	public static boolean isEmailInvalid(String email) {
		Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		return !regex.matcher(email).matches();
	}

	public static boolean isInteger(String number) {
		try {
		 Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
