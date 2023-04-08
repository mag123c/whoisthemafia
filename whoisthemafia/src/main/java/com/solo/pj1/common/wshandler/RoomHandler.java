package com.solo.pj1.common.wshandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.solo.pj1.room.service.RoomService;
import com.solo.pj1.user.dto.UserDTO;
import com.solo.pj1.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequestMapping("/room-ws")
public class RoomHandler extends TextWebSocketHandler{

	@Autowired
	RoomService roomService;
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	private static List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    private static List<Integer> idxList = new ArrayList<Integer>();
			
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Socket 연결");		
        int idx = Integer.parseInt(session.getAttributes().get("idx").toString());
        idxList.add(idx);
		sessions.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession single : sessions) {
			//1. 유저 입장
			if(message.getPayload().contains("join")) {
				int idx = Integer.parseInt(message.getPayload().split("/")[1]);
				//1-1. 정보 가져옴
				roomService.getGameRoom(idx);
				roomService.getRoomChat(idx);
				//1-2. 대기실에 정보 전달(유저숫자 ++ --체크만 하면됨)
				LobbyHandler.sendMsg("update/" + idx + "/+");
				
				//1.3. gameroom view
				UserDTO userDTO = new UserDTO();
				String id = message.getPayload().split("/")[2];
				userDTO = userService.getuserinfo(id);
				String nickname = userDTO.getNickname();
				String img = userDTO.getImg();
				if(img==null) {
					single.sendMessage(new TextMessage(nickname+"/"+"null"));
				}
				else single.sendMessage(new TextMessage(nickname+"/"+img));
			}
			//2. 퇴장 (퇴장 시 gameRoom에서 정보 지우기 - 상세view구현 후 나중에)
			else if(message.getPayload().contains("update")) {
				int idx = Integer.parseInt(message.getPayload().split("/")[1]);
				//2-1. room table 인원 삭제
				roomService.removeUser(idx);
				LobbyHandler.sendMsg("update/" + idx + "/-");
			}			
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//연결 해제
		logger.info("Socket 끊음");
		sessions.remove(session);
        int idx = Integer.parseInt(session.getAttributes().get("idx").toString());
        idxList.remove((Integer) idx);
        LobbyHandler.sendMsg("update/" + idx + "/-");
        
		//방에 한명이라도 사용자가 있을 경우 room 내부소켓 정보 갱신
		if(sessions.size() >= 1) {
			for(WebSocketSession single : sessions) {
				
			}
		}
	}	
}
