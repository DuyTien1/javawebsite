package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.NewModel;
import com.javaweb.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{
	NewModel findOne(long id);
	List<NewModel> findAll();
	List<NewModel> findAllAndSort(Pageble pageble);
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);
	void update(NewModel newModel);
	void delete(long id);
	int getTotalItem();
}
