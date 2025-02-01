package com.javaweb.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
	T MapRow(ResultSet rs);
}
