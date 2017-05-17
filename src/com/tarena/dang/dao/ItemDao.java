package com.tarena.dang.dao;
import java.sql.SQLException;

import com.tarena.dang.entity.Item;
public interface ItemDao {
	public void save(Item item)throws SQLException;
	public void deleteByOrderId(int orderId)throws SQLException;
}


