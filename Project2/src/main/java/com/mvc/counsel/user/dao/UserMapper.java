package com.mvc.counsel.user.dao;

import com.mvc.counsel.comm.MapperScanAnnotation;
import com.mvc.counsel.user.model.UserVO;


@MapperScanAnnotation
public interface UserMapper {
	/*
	 * 회원가입
	 */
	public void join(UserVO userVO);
	
	/*
	 * 로그인
	 */
	public UserVO login(String id);
	
	/*
	 * id 중복체크
	 */
	public int idCheck(String id);
}
