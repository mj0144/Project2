package com.mvc.counsel.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/*
	 * 첫페이지. index
	 */
	@RequestMapping("/")
	public String mainPage() {
		return "index";
	}
}
