package com.solo.pj1.room.service;

import java.util.List;

import com.solo.pj1.room.dto.ChatDTO;
import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomDTO;

public interface RoomService {

	void createRoom(RoomDTO dto);

	int getIdx();

	List<RoomDTO> searchRoom();

	RoomDTO getRoomInfo(int idx);
	
	List<GameRoomDTO> getGameRoom(int idx);

	List<ChatDTO> getRoomChat(int idx);

	void joinuser(int idx);

	boolean removeUser(int idx);

}
