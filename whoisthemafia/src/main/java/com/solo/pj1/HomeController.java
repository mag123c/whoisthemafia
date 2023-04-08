package com.solo.pj1;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(HttpSession ss) {
		if(ss.getAttribute("id")!=null) return ("lobby");
		return "home";
	}
		
}