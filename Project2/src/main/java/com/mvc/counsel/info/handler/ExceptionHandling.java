package com.mvc.counsel.info.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.counsel.info.exception.CounselBlankException;
import com.mvc.counsel.info.exception.DeleteException;
import com.mvc.counsel.info.exception.LockDeniedException;
import com.mvc.counsel.info.exception.UserURLDeniedException;

@ControllerAdvice
public class ExceptionHandling {

	final static Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

	/*
	 * CounselBlankException 예외처리.
	 * 다시 작성 페이지로
	 */
	@ExceptionHandler(CounselBlankException.class)
	public ModelAndView CounselExceptionHandler(CounselBlankException e, HttpServletRequest request) {
		logger.info("CounselBlankException..."+e.getMessage());
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMessage", e.getMessage());

		if (request.getServletPath().equals("/counsel/create")) {
			mav.setViewName("counsel/counselCreateForm");
		}
		return mav;
	}

	/*
	 * DeleteException 예외처리
	 * 400번 상태코드 담아 리턴
	 */
	@ExceptionHandler(DeleteException.class)
	public ResponseEntity<String> DeleteExceptionHandler(DeleteException e) {
		logger.info("DeleteException...");
		logger.info(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * LockdeniedException 예외처리. 비밀글 비밀번호 틀렸을 때.
	 * 400번 상태코드 담아 리턴
	 */
	@ExceptionHandler(LockDeniedException.class)
	public ResponseEntity<String> LockDeniedExceptionHandler(LockDeniedException e){
		logger.info("LockDeniedException...");
		logger.info(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/*
	 * url로 부정 접근 예외.
	 */
	@ExceptionHandler(UserURLDeniedException.class)
	public void userURLDeniedExceptionHandler(UserURLDeniedException e, HttpServletResponse response) throws IOException {
		logger.info("UserURLDeniedException...");
		logger.info(e.getMessage());
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('"+e.getMessage()+"'); history.go(-1); </script>");
        out.flush();
	}
	
}
