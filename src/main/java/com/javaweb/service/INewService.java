package com.javaweb.service;

import java.util.List;

import com.javaweb.model.NewModel;
import com.javaweb.paging.Pageble;

public interface INewService {
	List<NewModel> findAll();
	List<NewModel> findByCategoryId(Long categoryId);
	List<NewModel> findAllAndSort(Pageble pageble);
	NewModel save(NewModel newModel);
	NewModel update(NewModel newModel);
	void delete (long [] ids);
	int getTotalItem();
	NewModel findOne(long id);
}
