package com.tarena.dang.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tarena.dang.dao.ItemDao;
import com.tarena.dang.entity.Item;
import com.tarena.dang.util.DBUtil;

public class JDBCItemDao implements ItemDao{
	
	private static final String deleteByOrderId="delete from d_item where order_id=?";
	
	private static final String save="insert into d_item" +
	" (order_id,product_id,product_name,dang_price," +
	"product_num,amount) values(?,?,?,?,?,?)";
	public void save(Item item) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(save);
		ps.setInt(1,item.getOrderId());
		ps.setInt(2,item.getProductId());
		ps.setString(3,item.getProductName());
		ps.setDouble(4, item.getDangPrice());
		ps.setInt(5,item.getProductNum());
		ps.setDouble(6,item.getAmount());
		ps.executeUpdate();
	}
	public void deleteByOrderId(int orderId) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(deleteByOrderId);
		ps.setInt(1,orderId);
		ps.executeUpdate();
	}
	
	
}
