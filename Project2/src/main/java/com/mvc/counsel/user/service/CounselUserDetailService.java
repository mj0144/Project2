package com.mvc.counsel.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mvc.counsel.user.dao.UserMapper;
import com.mvc.counsel.user.model.UserVO;

//유저 정보 service 역할.
public class CounselUserDetailService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CounselUserDetailService.class);

	@Autowired
	UserMapper userMapper;

	// DB에서 유저정보 가져오기.
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		UserVO userVO = userMapper.login(id);
		// 회원 정보 없을 때.
		if (userVO == null) {
			logger.info("UsernameNotFoundException...회원정보가 없습니다");
			throw new UsernameNotFoundException("회원정보가 없습니다");
		}

		logger.info("login Access userId : " + userVO.getId());

		return userVO;

	}
}
