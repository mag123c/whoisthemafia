package com.solo.pj1.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.pj1.room.dto.ChatDTO;
import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomDTO;
import com.solo.pj1.room.repo.RoomDAO;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDAO roomDAO;
	
	@Override
	public void createRoom(RoomDTO dto) {
		roomDAO.createRoom(dto);
	}

	@Override
	public int getIdx() {
		return roomDAO.getIdx();
	}

	@Override
	public List<RoomDTO> searchRoom() {
		return roomDAO.searchRoom();
	}

	@Override
	public RoomDTO getRoomInfo(int idx) {
		return roomDAO.getRoomInfo(idx);
	}

	@Override
	public List<GameRoomDTO> getGameRoom(int idx) {
		return roomDAO.getGameRoom(idx);
	}

	@Override
	public List<ChatDTO> getRoomChat(int idx) {
		return roomDAO.getRoomChat(idx);
	}

	@Override
	public void joinuser(int idx) {
		roomDAO.joinuser(idx);
	}

	@Override
	public boolean removeUser(int idx) {
		return roomDAO.removeUser(idx);
	}	
	
}
