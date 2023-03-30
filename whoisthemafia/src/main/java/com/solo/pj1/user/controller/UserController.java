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
		if(dto.getId() == null || dto.getPw() == null || dto.getNickname() == null) {
			return "error";
		}
		
		String result = "";
		switch(userService.newUser(dto)) {
		case -1: result = "duplicateID";			
			break;
		case -2: result = "duplicateNickName";			
			break;
		case 0: result = "success";			
			break;
			default: result = "error";
		}
		return result;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(ModelAndView mv, HttpSession ss, UserDTO dto) {
		if(dto.getId() == null || dto.getPw() == null || userService.login(dto) != 0) {
			mv.addObject("message", "login_error");
			mv.setViewName("home");			
		}		
		else if(userService.login(dto) == 0) {
			ss.setAttribute("id", dto.getId());
			ss.setMaxInactiveInterval(60*180);
			mv.setViewName("redirect:/lobby");
		}
		return mv;		
	}
}
