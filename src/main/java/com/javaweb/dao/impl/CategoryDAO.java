package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.ICategoryDAO;
import com.javaweb.mapper.CategoryMapper;
import com.javaweb.mapper.NewMapper;
import com.javaweb.model.CategoryModel;
import com.javaweb.model.NewModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	@Override
	public List<CategoryModel> findall() {
		String sql = "select * from category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "SELECT * FROM category WHERE id =?";
		List<CategoryModel> categories = query(sql, new CategoryMapper(), id);
		return categories.isEmpty() ? null : categories.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code =?";
		List<CategoryModel> categories = query(sql, new CategoryMapper(), code);
		return categories.isEmpty() ? null : categories.get(0);
	}
}
