package com.pkproject.internetcourse.application.exceptions;

public class InvalidLoginException extends RuntimeException {

	private static final long serialVersionUID = -7839475328137926531L;

	public InvalidLoginException(String msg) {
		super(msg);
	}

}
