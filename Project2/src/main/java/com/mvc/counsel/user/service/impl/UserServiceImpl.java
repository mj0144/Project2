package com.mvc.counsel.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mvc.counsel.user.dao.UserMapper;
import com.mvc.counsel.user.model.UserVO;
import com.mvc.counsel.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	
	/*
	 * 아이디 중복체크 "no": 사용불가능, "yes" : 사용가능 
	 */
	@Override
	public String idCheck(String id) {
		if(userMapper.idCheck(id) == 1) {
			return "no";
		}else {
			return "yes";
		}
	}

	/*
	 * 회원가입
	 */
	@Override
	public void join(UserVO userVO) {
		userVO.setPassword(shaPasswordEncoder.encodePassword(userVO.getPassword(), null));
		userMapper.join(userVO);
	}
	

}
