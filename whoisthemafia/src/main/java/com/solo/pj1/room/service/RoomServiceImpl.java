package com.solo.pj1.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
