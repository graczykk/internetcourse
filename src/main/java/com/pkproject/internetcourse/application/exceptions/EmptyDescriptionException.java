package com.pkproject.internetcourse.application.exceptions;

public class EmptyDescriptionException extends RuntimeException {

	private static final long serialVersionUID = 4356111452024935785L;

	public EmptyDescriptionException(String message) {
		super(message);
	}
}
