package com.solo.pj1.room.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solo.pj1.room.dto.ChatDTO;
import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomDTO;

@Repository
public class RoomDAO {

	@Autowired
	SqlSession ss;
	
	public void createRoom(RoomDTO dto) {
		if(dto.getPw().length() == 0) ss.insert("room.create", dto);
		else ss.insert("room.create2", dto);
	}

	public int getIdx() {
		return ss.selectOne("room.get_idx");
	}

	public List<RoomDTO> searchRoom() {
		return ss.selectList("room.search");
	}

	public RoomDTO getRoomInfo(int idx) {
		return ss.selectOne("room.get_roominfo", idx);
	}

	public List<GameRoomDTO> getGameRoom(int idx) {
		return ss.selectList("room.get_gameroom", idx);
	}

	public List<ChatDTO> getRoomChat(int idx) {
		return ss.selectList("room.get_roomchat", idx);
	}

	public void joinuser(int idx) {
		ss.update("room.joinuser", idx);
	}

	public boolean removeUser(int idx) {
		int user = ss.selectOne("room.howmanyuser", idx);
		if(user > 1) {
			ss.update("room.remove_user", idx);
			return false;
		}
		else {
			ss.delete("room.remove_room", idx);
			return true;
		}
	}
}
