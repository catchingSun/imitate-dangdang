package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Product;

public interface BookDao {
	public List<Product> findByCatId(int catId, int page, int size) throws Exception;
	public int getTotalPage(int catId, int size) throws Exception;
	public List<Product> findAll() throws Exception;
	public Product findById(int id) throws SQLException;//-------------------------
}
