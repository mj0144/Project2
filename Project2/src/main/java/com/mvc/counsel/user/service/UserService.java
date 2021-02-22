package com.mvc.counsel.user.service;

import com.mvc.counsel.user.model.UserVO;

public interface UserService {
	/*
	 * 아이디 중복체크
	 */
	public String idCheck(String id);

	/*
	 * 회원가입
	 */
	public void join(UserVO userVO);

}
