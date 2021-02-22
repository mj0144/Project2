package com.mvc.counsel.info.exception;

public class LockDeniedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 비밀글 접근 시, 비번 틀렸을 때 예외.
	 */
	public LockDeniedException(String message) {
		super(message);
	}
}
