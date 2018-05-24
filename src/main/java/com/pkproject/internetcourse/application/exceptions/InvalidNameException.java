package com.pkproject.internetcourse.application.exceptions;

public class InvalidNameException extends RuntimeException {

	private static final long serialVersionUID = -822967333839678661L;

	public InvalidNameException(String msg) {
		super(msg);
	}

}
