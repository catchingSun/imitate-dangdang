package com.tarena.dang.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tarena.dang.dao.OrderDao;
import com.tarena.dang.entity.Address;
import com.tarena.dang.entity.Order;
import com.tarena.dang.util.DBUtil;
public class JDBCOrderDao implements OrderDao{

	private static final String save=
		"insert into d_order(user_id,status,order_time," +
		"order_desc,total_price,receive_name,full_address,postal_code,mobile," +
		"phone) values(?,?,?,?,?,?,?,?,?,?)";
		public void save(Order order) throws SQLException {
			PreparedStatement ps=DBUtil.getConnection()
			.prepareStatement(save,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getAddress().getUserId());
			ps.setInt(2,order.getStatus());
			ps.setLong(3,order.getOrderTime());
			ps.setString(4,order.getOrderDesc());
			ps.setDouble(5,order.getTotalPrice());
			ps.setString(6, order.getAddress().getReceiveName());
			ps.setString(7,order.getAddress().getFullAddress());
			ps.setString(8,order.getAddress().getPostalCode());
			
			
			ps.setString(9, order.getAddress().getMobile());
			ps.setString(10,order.getAddress().getPhone());
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			rs.next();
			int id=rs.getInt(1);
			order.setId(id);
	}
		
	private static final String findByUserId=
				"select * from d_order where user_id=?";
	public List<Order> findByUserId(int userId) throws SQLException {
		List<Order> orders=null;
		PreparedStatement ps=DBUtil.getConnection()
			.prepareStatement(findByUserId);
		ps.setInt(1,userId);
		ResultSet rs=ps.executeQuery();
		orders=new ArrayList<Order>();
		while(rs.next()){
			Address address=new Address();
			address.setUserId(rs.getInt("user_id"));
			address.setReceiveName(rs.getString("receive_name"));
			address.setFullAddress(rs.getString("full_address"));
			address.setPostalCode(rs.getString("postal_code"));
			address.setMobile(rs.getString("mobile"));
			address.setPhone(rs.getString("phone"));
			
			Order order=new Order();
			order.setId(rs.getInt("id"));
			order.setStatus(rs.getInt("status"));
			order.setOrderTime(rs.getLong("order_time"));
			order.setOrderDesc(rs.getString("order_desc"));
			order.setTotalPrice(rs.getDouble("total_price"));
			order.setAddress(address);
			orders.add(order);
		}
		return orders;
	}
	
	private static final String updateOrderState=
		"update d_order set status=? where id=?";
	public void updateOrderState(int id, 
				int state)throws SQLException {
		PreparedStatement ps=DBUtil.getConnection()
			.prepareStatement(updateOrderState);
		ps.setInt(1,state);
		ps.setInt(2,id);
		ps.executeUpdate();
	
	}
	
	private static final String findUserIdByOrderId=
					"select user_id from d_order where id=?";
	public Integer findUserIdByOrderId(int id) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection()
				.prepareStatement(findUserIdByOrderId);
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return rs.getInt("user_id");
		}
		return null;
	}
	
	private static final String deleteById=
		"delete from d_order where id=?";
	public void deleteById(int id) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection()
						.prepareStatement(deleteById);
		ps.setInt(1,id);
		ps.executeUpdate();
	}

	
}
