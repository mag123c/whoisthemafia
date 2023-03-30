package com.solo.pj1.user.service;

import com.solo.pj1.user.dto.UserDTO;

public interface UserService {

	int newUser(UserDTO dto);

	int login(UserDTO dto);

}
