package com.solo.pj1.room.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		return ss.selectOne("room.getIdx");
	}

	public List<RoomDTO> searchRoom() {
		return ss.selectList("room.search");
	}
}
