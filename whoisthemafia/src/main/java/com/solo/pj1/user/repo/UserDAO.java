package com.solo.pj1.user.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.solo.pj1.user.dto.UserDTO;

@Repository
public class UserDAO {

	@Autowired
	SqlSession ss;

	public int idcheck(UserDTO dto) {
		return ss.selectOne("user.idcheck", dto);
	}
	
	public int nicknamecheck(UserDTO dto) {
		return ss.selectOne("user.nicknamecheck",dto);
	}
	
	public void register(UserDTO dto) {						
		ss.insert("user.insert", dto);						
	}
	
	public String getPw(UserDTO dto) {
		return ss.selectOne("user.getpw", dto);
	}

	public int login(UserDTO dto) {		
		return ss.selectOne("user.getidx", dto);		
	}

	public UserDTO getuserinfo(String id) {
		return ss.selectOne("user.getinfo", id);
	}
}
