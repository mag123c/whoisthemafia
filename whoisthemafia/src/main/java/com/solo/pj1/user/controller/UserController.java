package com.solo.pj1.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solo.pj1.user.dto.UserDTO;
import com.solo.pj1.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	@ResponseBody
	public String register(UserDTO dto) {
		String result = userService.register(dto); 
		if(result==null) return "error";
		
		else return result;
	}
	
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
	public ModelAndView login(ModelAndView mv, HttpSession ss, UserDTO dto) {
		int idx = userService.login(dto);
		if(idx==-1) {
			mv.addObject("message", "login_error");
			mv.addObject("id", dto.getId());
			mv.setViewName("home");
			return mv;
		}	
		else {
			ss.setAttribute("id", dto.getId());
			ss.setAttribute("user_idx", idx);
			ss.setAttribute("nickname", userService.getuserinfo(dto.getId()).getNickname());
			ss.setMaxInactiveInterval(60*180);
			mv.setViewName("redirect:/lobby");
		}
		return mv;		
	}
}
