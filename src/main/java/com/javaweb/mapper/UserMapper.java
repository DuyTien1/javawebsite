package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.RoleModel;
import com.javaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel MapRow(ResultSet resultSet) {
		try {
			UserModel users = new UserModel();
			users.setId(resultSet.getLong("id"));
			users.setUsername(resultSet.getString("username"));
			users.setFullname(resultSet.getString("fullname"));
			users.setPassword(resultSet.getString("password"));
			users.setStatus(resultSet.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				users.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
//			if (resultSet.getTimestamp("modifieddate") != null) {
//				users.setModifiedDate(resultSet.getTimestamp("modifieddate"));
//			}
//
//			if (resultSet.getString("modifiedby") != null) {
//				users.setModifiedBy(resultSet.getString("modifiedby"));
//			}
			return users;
		} catch (SQLException e) {
			return null;
		}

	}
}
