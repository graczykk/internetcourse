package com.pkproject.internetcourse.application.exceptions;

public class PasswordNotMatchException extends RuntimeException {

	private static final long serialVersionUID = -4694453594085371866L;

	public PasswordNotMatchException(String msg) {
		super(msg);
	}
}
