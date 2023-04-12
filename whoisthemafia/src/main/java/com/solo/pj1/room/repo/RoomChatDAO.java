package com.solo.pj1.room.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solo.pj1.room.dto.RoomChatDTO;

@Repository
public class RoomChatDAO {

	@Autowired
	SqlSession ss;
	
	public List<RoomChatDTO> getinfo(int idx) {
		return ss.selectList("roomchat.getinfo", idx);
	}

	public void chatting(RoomChatDTO dto) {
		ss.insert("roomchat.chatting", dto);
	}
	
}
