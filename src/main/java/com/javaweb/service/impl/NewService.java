package com.javaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.ICategoryDAO;
import com.javaweb.dao.INewDAO;
import com.javaweb.model.CategoryModel;
import com.javaweb.model.NewModel;
import com.javaweb.paging.Pageble;
import com.javaweb.service.INewService;

public class NewService implements INewService{
	
	@Inject
	private INewDAO newDAO;

	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewModel> findAll() {
		return newDAO.findAll();
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		Long newId = newDAO.save(newModel);
		return newDAO.findOne(newId);
	}

	@Override
	public NewModel update(NewModel newModel) {
		NewModel oldNew = newDAO.findOne(newModel.getId());
		newModel.setCreatedDate(oldNew.getCreatedDate());
		newModel.setCreatedBy(oldNew.getCreatedBy());
		newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
//		CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
//		newModel.setCategoryId(category.getId());
		newDAO.update(newModel);
		return newDAO.findOne(newModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			newDAO.delete(id);
		}
	}

	@Override
	public List<NewModel> findAllAndSort(Pageble pageable) {
		return newDAO.findAllAndSort(pageable);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewModel findOne(long id) {
		NewModel newModel = newDAO.findOne(id);
		CategoryModel categoryModel =categoryDAO.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
		return newModel;
	}


}
