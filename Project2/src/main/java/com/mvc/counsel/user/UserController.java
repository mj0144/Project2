package com.mvc.counsel.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.counsel.user.model.UserVO;
import com.mvc.counsel.user.service.UserService;

@Controller
@RequestMapping("/counsel/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * 회원가입 폼 이동
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinPage() {
		return "user/join";
	}

	/*
	 * 회원가입
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public void join(UserVO userVO, HttpServletResponse response) throws IOException {
		userService.join(userVO);
		response.setContentType("text/html; charset=UTF-8");
		// 응답을 보낼 출력 스트림.
		PrintWriter out = response.getWriter();
		// 스트림에 텍스트 기록. 2단계 전으로(index)
		out.println("<script>alert('회원가입 완료.'); history.go(-2); </script>");
		// 클라이언트로 전송.
		out.flush();
	}

	/*
	 * 아이디 중복 체크 AJAX
	 */
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	@ResponseBody
	public String idCheck(String id) {
		return userService.idCheck(id);
	}

}
