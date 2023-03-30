package com.solo.pj1.room.service;

import java.util.List;

import com.solo.pj1.room.dto.RoomDTO;

public interface RoomService {

	void createRoom(RoomDTO dto);

	int getIdx();

	List<RoomDTO> searchRoom();

}
