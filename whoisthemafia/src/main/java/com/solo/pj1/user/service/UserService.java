package com.solo.pj1.user.service;

import com.solo.pj1.user.dto.UserDTO;

public interface UserService {

	String register(UserDTO dto);

	int login(UserDTO dto);

	UserDTO getuserinfo(String id);

}
