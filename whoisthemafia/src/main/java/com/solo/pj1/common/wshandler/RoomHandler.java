package com.solo.pj1.common.wshandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomChatDTO;
import com.solo.pj1.room.service.RoomService;
import com.solo.pj1.user.dto.UserDTO;
import com.solo.pj1.user.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
	private static List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());
    private static List<Integer> idxList = new ArrayList<Integer>();
    private static Queue<Integer> remainDiv = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8));
    private static Map<String,Integer> isinDiv = Collections.synchronizedMap(new HashMap<>());    
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Socket 연결");		
        int idx = Integer.parseInt(session.getAttributes().get("room_idx").toString());        
        idxList.add(idx);
        sessions.add(session);
        isinDiv.put((String) session.getAttributes().get("id"), remainDiv.poll());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		int idx = Integer.parseInt(message.getPayload().split("/")[1]);
		String id = message.getPayload().split("/")[2];
		List<GameRoomDTO> grDTO = roomService.getGameRoom(idx);
		List<RoomChatDTO> cDTO = roomService.getRoomChat(idx);
		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm");		
		System.out.println(grDTO.toString());
		System.out.println(message.getPayload());
		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm");

		for(WebSocketSession single : sessions) {
			//대기실에 정보 전달(유저숫자 ++ --체크만 하면됨 - 04.09 : 혹시 반복문안이라 로비에있는사람이 2명드갔을때 2명, 3명드가씅ㄹ떄 3명 ++되면 수정할것)
			LobbyHandler.sendMsg("update/" + idx + "/+");
			//1. 유저 입장
			if(message.getPayload().contains("join")) {
				for(GameRoomDTO gr : grDTO) {					
					String nickname = gr.getNickname();
					String img = gr.getImg();
					System.out.println(nickname);
					System.out.println(img);
					if(img==null) {
						single.sendMessage(new TextMessage(nickname+"/"+"null/"+isinDiv.get(single.getAttributes().get("id"))));
					}
					else single.sendMessage(new TextMessage(nickname+"/"+img+"/"+isinDiv.get(single.getAttributes().get("id"))));
				}						
				
			}
			//2. 퇴장 (퇴장 시 gameRoom에서 정보 지우기 - 상세view구현 후 나중에)
			else if(message.getPayload().contains("update")) {				
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
		remainDiv.add(isinDiv.get(session.getAttributes().get("id")));
		isinDiv.remove(session.getAttributes().get("id"));
        int idx = Integer.parseInt(session.getAttributes().get("room_idx").toString());
        idxList.remove((Integer) idx);        
        LobbyHandler.sendMsg("update/" + idx + "/-");
        
		//방에 한명이라도 사용자가 있을 경우 room 내부소켓 정보 갱신
		if(sessions.size() >= 1) {
			for(WebSocketSession single : sessions) {
				
			}
		}
	}	
}
