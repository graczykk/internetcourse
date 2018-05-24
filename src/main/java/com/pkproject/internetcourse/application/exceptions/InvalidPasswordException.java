package com.pkproject.internetcourse.application.exceptions;

public class InvalidPasswordException extends RuntimeException {

	private static final long serialVersionUID = 6490520993478642005L;

	public InvalidPasswordException(String msg) {
		super(msg);
	}

}
