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
import com.solo.pj1.user.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    private static Queue<Integer> remainDiv = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8));
//    private static Map<String,Integer> isinDiv = Collections.synchronizedMap(new HashMap<>());
    @SuppressWarnings("serial")
	private static Map<Integer, Integer> readyStatus = new HashMap<Integer, Integer>() {
    	{
    		put(1, 0);
    		put(2, 0);
    		put(3, 0);
    		put(4, 0);
    		put(5, 0);
    		put(6, 0);
    		put(7, 0);
    		put(8, 0);
    	}
    };
    private static int readySum=0;
    
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Socket 연결");		
        int idx = Integer.parseInt(session.getAttributes().get("room_idx").toString());        
        idxList.add(idx);
        sessions.add(session);
        readySum++;
//        isinDiv.put((String) session.getAttributes().get("id"), remainDiv.poll());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		/* 채팅 쳤을 때, 채팅 메서드로 이동 */
		if(message.getPayload().contains("userMSG")) {
			chatMSG(message.getPayload(), session);
			return;
		}
		/* ready user 수 체크해서 게임 start */
		if(message.getPayload().contains("readyStatus")){
			ready(message);
			return;
		}
		
		if(message.getPayload().contains("start")) {
			start(session, message.getPayload().split("/")[1]);
			return;
		}

		
		int idx = Integer.parseInt(message.getPayload().split("/")[1]);
		String id = message.getPayload().split("/")[2];
		List<GameRoomDTO> grDTO = roomService.getGameRoom(idx);
		List<RoomChatDTO> cDTO = roomService.getRoomChat(idx);
		int tmp = 0;
		for(WebSocketSession single : sessions) {
			//1. 유저 입장
			if(message.getPayload().contains("join")) {
				//대기실에 정보 전달(유저숫자 ++ --체크만 하면됨 - 04.09 : 혹시 반복문안이라 로비에있는사람이 2명드갔을때 2명, 3명드가씅ㄹ떄 3명 ++되면 수정할것)
				if(tmp==0) {
					LobbyHandler.sendMsg("update/" + idx + "/+");
					tmp++;
				}
				//1-1. 처음 입장한애
				if(single.getAttributes().get("id").equals(id)) {
					/* 입장 시  게임방의 정보 받아오기 */
					for(GameRoomDTO gr : grDTO) {					
						String nickname = gr.getNickname();
						String img = gr.getImg();
//						int where = isinDiv.get(single.getAttributes().get("id"));
						if(img==null) {
							single.sendMessage(new TextMessage(nickname+"/"+"null"));
						}
						else single.sendMessage(new TextMessage(nickname+"/"+img));
					}
					/* 입장시 채팅 받아오기 */
					if(cDTO.size() > 0) {
						for(RoomChatDTO rc : cDTO) {
							System.out.println(rc.toString());
							String nickname = userService.getuserinfo(rc.getUser_id()).getNickname();
							String msg = rc.getMsg();
							single.sendMessage(new TextMessage("joinMSG/"+nickname+" : "+msg));						
						}
					}
				}
				//1-2. 이미 있는애
				else {					
					String nickname = userService.getuserinfo(id).getNickname();
					String img = userService.getuserinfo(id).getImg();
//					int where = isinDiv.get(single.getAttributes().get("id"));
					if(img==null) {
						single.sendMessage(new TextMessage(nickname+"/"+"null"));
					}
					else single.sendMessage(new TextMessage(nickname+"/"+img));
				}
				
			}
			//2. 퇴장 (퇴장 시 gameRoom에서 정보 지우기 - 상세view구현 후 나중에)
			else if(message.getPayload().contains("update")) {				
				//2-1. room table 인원 삭제
				roomService.removeUser(id, idx);
				LobbyHandler.sendMsg("update/" + idx + "/-");
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//연결 해제
		logger.info("Socket 끊음");
		String nickname = userService.getuserinfo((String) session.getAttributes().get("id")).getNickname();
		sessions.remove(session);
//		int where = isinDiv.get(session.getAttributes().get("id"));
//		remainDiv.add(isinDiv.get(session.getAttributes().get("id")));
//		isinDiv.remove(session.getAttributes().get("id"));
        int idx = Integer.parseInt(session.getAttributes().get("room_idx").toString());
        idxList.remove((Integer) idx);        
        LobbyHandler.sendMsg("update/" + idx + "/-");
        readySum--;
        
		//방에 한명이라도 사용자가 있을 경우 room 내부소켓 정보 갱신
		if(sessions.size() >= 1) {
			for(WebSocketSession single : sessions) {
				single.sendMessage(new TextMessage("userout/"+nickname));
			}
		}
	}
	
	/* userMSG -> view */
	private void chatMSG(String userMSG, WebSocketSession session) throws Exception {
		for(WebSocketSession ss : sessions) {
			ss.sendMessage(new TextMessage(userMSG));
		}				
	}
	
	/* user ready status */
	/* 유저가 레디한상태로 나갈때, 안한상태로 나갈때 별로 조건을 못줘서 처리를 빡세게 못하는 중 - 04.11*/
	private void ready(TextMessage message) {
		System.out.println(message.getPayload());
		String pm = message.getPayload().split("/")[1];
		int divnum = Integer.parseInt((String) message.getPayload().split("/")[2]);
		switch(pm){
			case "+":
			break;
//			case "-":
//				readyStatus.put(divnum, readyStatus.getOrDefault(divnum, 0) -1);
//				readySum--;
//			break;
		}		
		return;
	}
	
	private void start(WebSocketSession session, String roomidx) throws Exception {
		if(readySum==8) {
			int idx = Integer.parseInt(roomidx);
			roomService.updateRole(idx);
		}
		for(WebSocketSession ws : sessions) {
			if(readySum==8) {
				ws.sendMessage(new TextMessage("gamestatus/start"));
			}
			else {
				if(ws.getAttributes().get("id").equals(session.getAttributes().get("id"))) {
					ws.sendMessage(new TextMessage("gamestatus/fail"));
					return;
				}				
			}
		}
	}
}
