package com.mvc.counsel.info.exception;

public class DeleteException extends Exception {
	private static final long serialVersionUID = 1906728757346724702L;

	/*
	 * 삭제할 때, 삭제한 게시물 없을 떄 예외
	 */
	public DeleteException(String message) {
		super(message);
	}
}
