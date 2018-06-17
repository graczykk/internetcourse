package com.pkproject.internetcourse.application.exceptions;

public class SqlExceptionRuntime extends RuntimeException {

	private static final long serialVersionUID = -8900608561594593934L;

	public SqlExceptionRuntime(String message) {
		super(message);
	}
}
