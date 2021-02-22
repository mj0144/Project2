package com.mvc.counsel.info.exception;

public class UserURLDeniedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 다른 사용자가 url로 접근시 예외.
	 */
	public UserURLDeniedException(String message) {
		super(message);
	}
}
