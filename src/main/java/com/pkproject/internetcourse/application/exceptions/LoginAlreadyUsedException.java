package com.pkproject.internetcourse.application.exceptions;

public class LoginAlreadyUsedException extends RuntimeException {

	private static final long serialVersionUID = -429188356723799114L;

	public LoginAlreadyUsedException(String message) {
		super(message);
	}
}
