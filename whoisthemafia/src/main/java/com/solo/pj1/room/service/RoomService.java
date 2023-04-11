package com.solo.pj1.room.service;

import java.util.List;

import com.solo.pj1.room.dto.RoomChatDTO;
import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomDTO;

public interface RoomService {

	int createRoom(RoomDTO dto);

	List<RoomDTO> searchRoom();

	RoomDTO getRoomInfo(int idx);
	
	List<GameRoomDTO> getGameRoom(int idx);

	List<RoomChatDTO> getRoomChat(int idx);
	
	void joinuser(String id, int idx);

	void removeUser(String id, int idx);

	void delroom(int idx);

	String chatting(RoomChatDTO dto);
}
