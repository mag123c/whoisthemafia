package com.solo.pj1.room.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solo.pj1.room.dto.RoomChatDTO;
import com.solo.pj1.room.dto.RoomDTO;
import com.solo.pj1.room.service.RoomService;

@Controller
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@RequestMapping(value="/lobby", method=RequestMethod.GET)
	public String lobby() {
		//입장 시 0명인 방 제거 -> 임시(더좋은방법 생기면 바꿀듯)
		roomService.delroom(0);
		return "lobby";
	}
	
	@RequestMapping(value="/room", method=RequestMethod.POST)
	@ResponseBody
	public int createRoom(HttpSession ss, RoomDTO dto) {
		return roomService.createRoom(dto);		
	}
	
	@RequestMapping(value="/rooms/{idx}", method=RequestMethod.GET)
	public ModelAndView viewRoom(HttpSession ss, @PathVariable int idx) {
		ss.setAttribute("room_idx", idx);
		ModelAndView mv = new ModelAndView("gameroom");
		return mv;
	}
	
	@RequestMapping(value="/rooms/{idx}", method=RequestMethod.POST)
	@ResponseBody
	public void joinRoom(String id, @PathVariable int idx) {
		//방 정보(유저 수) 변경
		roomService.joinuser(id, idx);		
	}
	
	@RequestMapping(value="/rooms/{idx}", method=RequestMethod.PUT)
	@ResponseBody
	public void removeUser(@PathVariable int idx, @RequestBody String id) {
		id = id.substring(id.indexOf("=")+1, id.indexOf("&"));
		//방 정보(유저 수) 변경
		roomService.removeUser(id, idx);
	}
	
	@RequestMapping(value="/rooms/{idx}/chat", method=RequestMethod.PUT, produces="application/text; charset=utf8")
	@ResponseBody
	public String chatting(@PathVariable int idx, @RequestBody RoomChatDTO dto) {
		return roomService.chatting(dto);
	}
		
}
