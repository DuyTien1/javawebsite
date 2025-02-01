package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.INewDAO;
import com.javaweb.mapper.NewMapper;
import com.javaweb.model.NewModel;
import com.javaweb.paging.Pageble;
import org.apache.commons.lang.StringUtils;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public NewModel findOne(long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public List<NewModel> findAll() {
		String sql = "SELECT * FROM news";
		return query(sql, new NewMapper());
	}

	@Override
	public List<NewModel> findAllAndSort(Pageble pageble) {
//		String sql = "SELECT * FROM news LIMIT ?, ?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append("ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT ?, ?");
			return query(sql.toString(), new NewMapper(), pageble.getOffset(), pageble.getLimit());
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
//		String sql = "INSERT INTO news (title, content, thumbnail, shortdescription, categoryid) values (?, ?, ?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" values (?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), newModel.getThumbnail(),
				newModel.getShortDescription(), newModel.getCategoryId(), newModel.getCreatedDate(),
				newModel.getCreatedBy());
	}

	@Override
	public void update(NewModel newModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" modifieddate = ?, modifiedby = ?, createddate = ?, createdby = ? WHERE id = ?");
		update(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCategoryId(), newModel.getModifiedDate(), 
				newModel.getModifiedBy(), newModel.getCreatedDate(), newModel.getCreatedBy(), newModel.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

}
