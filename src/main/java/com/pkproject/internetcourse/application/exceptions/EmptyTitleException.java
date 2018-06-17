package com.pkproject.internetcourse.application.exceptions;

public class EmptyTitleException extends RuntimeException {

	private static final long serialVersionUID = -4297036494934281327L;

	public EmptyTitleException(String message) {
		super(message);
	}
}
