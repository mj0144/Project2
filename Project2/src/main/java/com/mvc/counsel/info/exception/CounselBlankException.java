package com.mvc.counsel.info.exception;

public class CounselBlankException extends Exception {

	private static final long serialVersionUID = 1L;

	/*
	 * 빈칸 있을 떄 예외
	 */
	public CounselBlankException(String message) {
		super(message);
	}

}
