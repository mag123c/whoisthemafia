package com.solo.pj1.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solo.pj1.room.dto.RoomDTO;
import com.solo.pj1.room.service.RoomService;

@Controller
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@RequestMapping("/lobby")
	public String lobby() {
		return "lobby";
	}
	
	@RequestMapping(value="/room", method=RequestMethod.POST)
	@ResponseBody
	public void createRoom(RoomDTO dto, String rname) {
		System.out.println(dto.toString());
		roomService.createRoom(dto);
		int idx = roomService.getIdx();
	}
	
//	@RequestMapping("/room/{idx}")
//	public String toRoom(RoomDTO dto, @PathVariable int idx) {
//		return "gameroom";
//	}	
}
