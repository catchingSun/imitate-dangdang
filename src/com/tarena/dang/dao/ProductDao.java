package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Product;

public interface ProductDao {
	public List<Product> findNewProduct(int size) throws SQLException;
	public List<Product> findHotProduct(int size) throws SQLException;
	List<Product> findNewHotProduct(int size, int month) throws SQLException;
	public Product findById(int pid)throws SQLException;
}
