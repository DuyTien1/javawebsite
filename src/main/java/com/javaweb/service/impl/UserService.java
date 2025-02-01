package com.javaweb.service.impl;

import javax.inject.Inject;

import com.javaweb.dao.IUserDAO;
import com.javaweb.model.UserModel;
import com.javaweb.service.IUserService;

public class UserService implements IUserService{
	
	@Inject private IUserDAO userDAO;

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		return userDAO.findByUsernameAndPasswordAndStatus(username, password, status);
	}

}
