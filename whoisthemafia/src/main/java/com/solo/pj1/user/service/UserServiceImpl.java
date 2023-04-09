package com.solo.pj1.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.solo.pj1.user.dto.UserDTO;
import com.solo.pj1.user.repo.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	BCryptPasswordEncoder bcpe;
	
	@Override
	public String register(UserDTO dto) {
		if(dto.getId() == null || dto.getPw() == null || dto.getNickname() == null) {
			return null;
		}
		dto.setPw(bcpe.encode(dto.getPw()));
		
		if(userDAO.idcheck(dto) > 0) return "duplicateID";
		else if(userDAO.nicknamecheck(dto) > 0) return "duplicateNickName";
		else {
			userDAO.register(dto);
			return "success";
		}		
	}

	@Override
	public int login(UserDTO dto) {
		if(dto.getId() == null || dto.getPw() == null) {
			return -1;
		}
		if(userDAO.idcheck(dto) == 1) {
			if(bcpe.matches(dto.getPw(), userDAO.getPw(dto))) {
				return userDAO.login(dto);
			}
			else return -1;
		}
		else return -1;
	}

	@Override
	public UserDTO getuserinfo(String id) {
		return userDAO.getuserinfo(id);
	}

}
