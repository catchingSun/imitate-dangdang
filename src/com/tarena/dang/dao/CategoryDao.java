package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Category;

public interface CategoryDao {
	public List<Category> findByParentId(Integer pid);
	public String getCategoryNameById(int id)throws SQLException;
	public List<Category> findAll() throws Exception;
}
