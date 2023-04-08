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
	@Autowired
	BCryptPasswordEncoder bcpe;
	
	public int newUser(UserDTO dto) {
		dto.setPw(bcpe.encode(dto.getPw()));
		//-1 : id중복, -2 : nickname 중복, 0 : 가입승인
		if((Integer)(ss.selectOne("user.idcheck", dto)) > 0){
			return -1;
		}
		else if((Integer)(ss.selectOne("user.nicknamecheck", dto)) > 0){
			return -2;
		}
		else {				
			ss.insert("user.insert", dto);
			return 0;
		}				
	}

	public int login(UserDTO dto) {		
		//0 : 로그인성공, -1 : 실패
		if((Integer)(ss.selectOne("user.idcheck", dto)) == 1) {			
			if(bcpe.matches(dto.getPw(), (String)ss.selectOne("user.getpw", dto))) {
				return ss.selectOne("user.getidx", dto);
			}
			else return -1;
		}
		else return -1;
	}

	public UserDTO getuserinfo(String id) {
		return ss.selectOne("user.getinfo", id);
	}
}
