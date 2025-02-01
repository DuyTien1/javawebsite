package com.javaweb.dao;

import com.javaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);

}
