package com.mvc.counsel.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedPage implements AccessDeniedHandler {

	private static final Logger logger = LoggerFactory.getLogger(AccessDeniedPage.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();
		String url = request.getRequestURI();

		logger.info("요청한 URL : " + url + ", 요청한 User ID : " + username);

		response.sendRedirect("/comm/error/authDeniedPage");

	}

}
