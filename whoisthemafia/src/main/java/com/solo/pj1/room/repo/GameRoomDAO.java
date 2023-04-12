package com.solo.pj1.room.repo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solo.pj1.room.dto.GameRoomDTO;
import com.solo.pj1.room.dto.RoomChatDTO;

@Repository
public class GameRoomDAO {

	@Autowired
	SqlSession ss;

	public void createAndJoin(GameRoomDTO grDTO) {
		ss.insert("gameroom.c_j", grDTO);
	}	
	
	public List<GameRoomDTO> getGameRoom(int idx) {
		return ss.selectList("gameroom.getinfo", idx);
	}

	public void userOut(GameRoomDTO grDTO) {
		ss.delete("gameroom.userout", grDTO);		
	}

	public void updateRole(Map<String, Integer> mafia) {
		ss.update("gameroom.updaterole", mafia);
	}

	public int getUserIdx(Map<String, Integer> map) {
		return ss.selectOne("gameroom.getuseridx", map);
	}
	
}
