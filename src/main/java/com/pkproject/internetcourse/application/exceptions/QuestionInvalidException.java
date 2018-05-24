package com.pkproject.internetcourse.application.exceptions;

public class QuestionInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2929113976191568902L;

	public QuestionInvalidException(String message) {
		super(message);
	}
}
