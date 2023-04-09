package com.solo.pj1.room.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solo.pj1.room.dto.RoomChatDTO;
import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomDTO;
import com.solo.pj1.room.repo.GameRoomDAO;
import com.solo.pj1.room.repo.RoomChatDAO;
import com.solo.pj1.room.repo.RoomDAO;
import com.solo.pj1.user.dto.UserDTO;
import com.solo.pj1.user.repo.UserDAO;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDAO roomDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	GameRoomDAO grDAO;
	@Autowired
	RoomChatDAO rcDAO;
	
	@Transactional
	@Override
	public int createRoom(RoomDTO dto) {
		UserDTO userDTO = userDAO.getuserinfo(dto.getRhost());
		dto.setRhost(userDTO.getNickname());		
		if(dto.getPw().length() == 0) roomDAO.createRoom1(dto);
		else roomDAO.createRoom2(dto);
		
		grDAO.createAndJoin(creategrDTO(userDTO.getIdx(), roomDAO.getIdx()));
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
		return grDAO.getGameRoom(idx);
	}

	@Override
	public List<RoomChatDTO> getRoomChat(int idx) {
		return rcDAO.getinfo(idx);
	}

	@Transactional
	@Override
	public void joinuser(String id, int idx) {
		roomDAO.joinuser(idx);
		int uidx = userDAO.getuserinfo(id).getIdx();
		grDAO.createAndJoin(creategrDTO(uidx, idx));
	}

	@Transactional
	@Override
	public void removeUser(int idx) {
		int user = roomDAO.howmanyuser(idx);
		if(user > 1) {
			roomDAO.removeuser(idx);
		}
		else {
			roomDAO.removeroom(idx);
		}
	}

	@Override
	public void delroom(int idx) {
		roomDAO.removeroom(idx);
	}
	
	
	public GameRoomDTO creategrDTO(int user_idx, int room_idx) {
		GameRoomDTO grDTO = new GameRoomDTO();
		grDTO.setUser_idx(user_idx);
		grDTO.setRoom_idx(room_idx);	
		return grDTO;
	}
}
