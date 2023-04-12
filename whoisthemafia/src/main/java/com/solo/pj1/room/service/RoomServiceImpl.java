package com.solo.pj1.room.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public GameRoomDTO creategrDTO(int user_idx, int room_idx) {
		GameRoomDTO grDTO = new GameRoomDTO();
		grDTO.setUser_idx(user_idx);
		grDTO.setRoom_idx(room_idx);	
		return grDTO;
	}

	
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
	public void removeUser(String id, int idx) {
		int user = roomDAO.howmanyuser(idx);
		if(user > 1) {
			roomDAO.removeuser(idx);
		}
		else {
			roomDAO.removeroom(idx);
		}
		int uidx = userDAO.getuserinfo(id).getIdx();
		GameRoomDTO grDTO = new GameRoomDTO();
		grDTO.setUser_idx(uidx);
		grDTO.setRoom_idx(idx);
		grDAO.userOut(grDTO);
	}

	@Override
	public void delroom(int idx) {
		roomDAO.removeroom(idx);
	}

	@Override
	public String chatting(RoomChatDTO dto) {
		UserDTO udto = userDAO.getuserinfo(dto.getUser_id());
		dto.setUser_idx(udto.getIdx());
		rcDAO.chatting(dto);
		return "userMSG/"+udto.getNickname() + " : " + dto.getMsg();
	}

	@Transactional
	@Override
	public void updateRole(int idx) {
    	Set<Integer> set = new HashSet<>();
    	while(set.size() <= 1) {
    		Double d = Math.random() * 8 + 1;
    		set.add(d.intValue());
    	}
    	List<Integer> mafiaNum = new ArrayList<>(set);
    	Map<String, Integer> mafia1 = new HashMap<>();
    	Map<String, Integer> mafia2 = new HashMap<>();
    	mafia1.put("idx", idx);
    	mafia1.put("mafia_idx", mafiaNum.get(0)-1);
    	mafia2.put("idx", idx);
    	mafia2.put("mafia_idx", mafiaNum.get(1)-1);
    	mafia1.put("mafia_idx", grDAO.getUserIdx(mafia1));
    	mafia2.put("mafia_idx", grDAO.getUserIdx(mafia2));
    	grDAO.updateRole(mafia1);
    	grDAO.updateRole(mafia2);
    }
}
