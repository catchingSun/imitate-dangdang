package com.tarena.dang.dao;
import java.sql.SQLException;

import com.tarena.dang.entity.Order;
public interface OrderDao {
	public void save(Order order)throws SQLException;
}




