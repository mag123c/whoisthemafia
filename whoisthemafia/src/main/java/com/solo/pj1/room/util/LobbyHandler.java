package com.solo.pj1.room.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.solo.pj1.room.dto.RoomDTO;
import com.solo.pj1.room.service.RoomService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequestMapping("/lobby-ws")
public class LobbyHandler extends TextWebSocketHandler{

	@Autowired
	RoomService roomService;
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();	
		
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Socket 연결");		
		sessions.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession single : sessions) {
			List<RoomDTO> roomList = new ArrayList<>();
			roomList = roomService.searchRoom();
			for(RoomDTO dto : roomList) {
				System.out.println(dto.toString());
				String rname = dto.getRname();
				String pw = dto.getPw();
				int people = dto.getPeople();
				TextMessage sendMsg = new TextMessage(rname + "/" + pw + "/" + people);
				single.sendMessage(sendMsg);
			}			
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//연결 해제
		logger.info("Socket 끊음");
		sessions.remove(session);
	}

}
