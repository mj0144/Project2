package com.mvc.counsel.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.mvc.counsel.user.model.UserVO;
import com.mvc.counsel.user.service.CounselUserDetailService;

public class CounselUserAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CounselUserAuthenticationProvider.class);

	@Autowired
	private CounselUserDetailService counselUserDetailService;

	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// 로그인 폼에서 입력한 id, password
		String id = authentication.getName();
		String password = (String) authentication.getCredentials();

		// DB의 값과 id 비교
		UserVO userVO = (UserVO) counselUserDetailService.loadUserByUsername(id);

	

		// 비밀번호 틀렸을 때. 로그출력
		if (!shaPasswordEncoder.isPasswordValid(password, userVO.getPassword(), null)) {
			logger.error("Password error ID : " + userVO.getId() + "..비밀번호가 틀립니다");
			throw new BadCredentialsException("비밀번호가 틀립니다.");
		}

		logger.info("login succeess ID : " + userVO.getId());

		// 로그인한 정보 리턴
		return new UsernamePasswordAuthenticationToken(id, password, userVO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	




}
