package com.pkproject.internetcourse.application.exceptions;

public class InvalidEmailException extends RuntimeException {

	private static final long serialVersionUID = 5581055773253116724L;

	public InvalidEmailException(String msg) {
		super(msg);
	}

}
