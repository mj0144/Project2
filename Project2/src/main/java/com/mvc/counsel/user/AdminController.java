package com.mvc.counsel.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.counsel.user.model.UserVO;
import com.mvc.counsel.user.service.UserService;

@Controller
@RequestMapping("/counsel/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	/*
	 *  admin 계정에서 사용자 추가.
	 */
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public void addUser(UserVO userVO, HttpServletResponse response) throws IOException {
		userService.join(userVO);
		response.setContentType("text/html; charset=UTF-8");
		//응답을 보낼 출력 스트림.
        PrintWriter out = response.getWriter();
        //스트림에 텍스트 기록.
        out.println("<script>alert('사용자 추가 완료.'); history.go(-1); </script>");
        //클라이언트로 전송.
        out.flush();
	}
}
