package com.solo.pj1.room.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solo.pj1.room.dto.RoomChatDTO;
import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomDTO;

@Repository
public class RoomDAO {

	@Autowired
	SqlSession ss;
	
	public void createRoom1(RoomDTO dto) {
		ss.insert("room.create", dto);		
	}
	
	public void createRoom2(RoomDTO dto) {
		ss.insert("room.create2", dto);
	}	

	public int getIdx() {
		return ss.selectOne("room.get_idx");
	}

	public List<RoomDTO> searchRoom() {
		return ss.selectList("room.search");
	}

	public RoomDTO getRoomInfo(int idx) {
		return ss.selectOne("room.getinfo", idx);
	}

	public void joinuser(int idx) {
		ss.update("room.joinuser", idx);
	}
	
	public int howmanyuser(int idx) {
		return ss.selectOne("room.howmanyuser", idx);
	}
	
	public void removeuser(int idx) {
		ss.update("room.remove_user", idx);
	}
	
	public void removeroom(int idx) {
		ss.delete("room.remove_room", idx);
	}

}
