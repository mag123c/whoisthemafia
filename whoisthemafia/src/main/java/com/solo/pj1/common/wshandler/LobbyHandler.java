package com.solo.pj1.common.wshandler;

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

import java.io.IOException;
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
	private static List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();	
		
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Socket 연결");
		sessions.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession single : sessions) {
			//1. lobby입장 시 전체로드
			if(single.getAttributes().get("id").equals(message.getPayload())) {
				List<RoomDTO> roomList = new ArrayList<>();
				roomList = roomService.searchRoom();
				for(RoomDTO dto : roomList) {
					int idx = dto.getIdx();
					String rname = dto.getRname();
					String pw = dto.getPw();
					int people = dto.getPeople();
					TextMessage sendMsg = new TextMessage("join/" + idx + "/" + rname + "/" + pw + "/" + people);
					single.sendMessage(sendMsg);
				}	
			}
			//2. 생성 시 해당 방 정보만 update
			else if(message.getPayload().contains("create")) {
				int newidx = Integer.parseInt(message.getPayload().split("/")[1]);
				RoomDTO newRoom = new RoomDTO();				
				newRoom = roomService.getRoomInfo(newidx);
				int idx = newRoom.getIdx();
				String rname = newRoom.getRname();
				String pw = newRoom.getPw();
				int people = newRoom.getPeople();
				TextMessage sendMsg = new TextMessage("create/" + idx + "/" + rname + "/" + pw + "/" + people);
				single.sendMessage(sendMsg);				
			}
			//3. 방에서 인원 빠졌을 때 db-- 처리
			else if(message.getPayload().contains("--/")) {
				int idx = Integer.parseInt(message.getPayload().split("/")[1]);
				//3-1. 방 삭제의 경우 view update (SQL : true delete / false update)
				if(roomService.removeUser(idx)) {
	                single.sendMessage(new TextMessage("deleteroom/"+idx));
				}
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//연결 해제
		logger.info("Socket 끊음");
		sessions.remove(session);
	}

	public static void sendMsg(String message) {
		for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                logger.error("Failed to send message.", e);
            }
        }
	}

}
