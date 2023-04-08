package com.solo.pj1.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.pj1.user.dto.UserDTO;
import com.solo.pj1.user.repo.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public int newUser(UserDTO dto) {
		return userDAO.newUser(dto);
	}

	@Override
	public int login(UserDTO dto) {
		return userDAO.login(dto);
	}

	@Override
	public UserDTO getuserinfo(String id) {
		return userDAO.getuserinfo(id);
	}

}
