package com.pkproject.internetcourse.application.exceptions;

public class AuthenticationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1233175192421621816L;

	public AuthenticationFailedException(String msg) {
		super(msg);
	}
}
