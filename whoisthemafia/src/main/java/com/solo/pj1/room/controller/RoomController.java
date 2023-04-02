package com.solo.pj1.room.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solo.pj1.room.dto.RoomDTO;
import com.solo.pj1.room.service.RoomService;

@Controller
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@RequestMapping(value="/lobby", method=RequestMethod.GET)
	public String lobby() {
		return "lobby";
	}
	
	@RequestMapping(value="/room", method=RequestMethod.POST)
	@ResponseBody
	public int createRoom(RoomDTO dto, String rname) {
		roomService.createRoom(dto);
		return roomService.getIdx();
	}
	
	@RequestMapping(value="/room/{idx}", method=RequestMethod.GET)
	public ModelAndView viewRoom(HttpSession ss, @PathVariable int idx) {
		ss.setAttribute("idx", idx);
		ModelAndView mv = new ModelAndView("gameroom", "idx", idx);
		return mv;
	}
	
	@RequestMapping(value="/room/{idx}", method=RequestMethod.POST)
	@ResponseBody
	public boolean joinRoom(@PathVariable int idx) {
		//방 정보(유저 수) 변경
		roomService.joinuser(idx);
		return true;
	}	
}
