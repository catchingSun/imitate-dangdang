package com.tarena.dang.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.AddressDao;
import com.tarena.dang.entity.Address;
import com.tarena.dang.util.DBUtil;
public class JDBCAddressDao implements AddressDao{
	
	private static final String save=
		"insert into d_receive_address(user_id," +
		"receive_name,full_address,postal_code,mobile,phone) " +
		"values(?,?,?,?,?,?)";
	public void save(Address address) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection()
			.prepareStatement(save,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1,address.getUserId());
		ps.setString(2,address.getReceiveName());
		ps.setString(3,address.getFullAddress());
		ps.setString(4,address.getPostalCode());
		ps.setString(5,address.getMobile());
		ps.setString(6,address.getPhone());
		ps.executeUpdate();
		//获取自动增长的id
		ResultSet rs=ps.getGeneratedKeys();
		
		rs.next();
		int id=rs.getInt(1);
		address.setId(id);
	}
	
	private static final String findByUserId=
		"select * from d_receive_address where user_id=?";
	public List<Address> findAddressByUserId(int userId) 
									throws SQLException {
		PreparedStatement ps=DBUtil.getConnection()
						.prepareStatement(findByUserId);
		ps.setInt(1,userId);
		ResultSet rs=ps.executeQuery();
		List<Address> addresses=new ArrayList<Address>();
		while(rs.next()){
			Address address=new Address();
			address.setId(rs.getInt("id"));
			address.setUserId(rs.getInt("user_id"));
			address.setReceiveName(rs.getString("receive_name"));
			address.setFullAddress(rs.getString("full_address"));
			address.setPostalCode(rs.getString("postal_code"));
			address.setMobile(rs.getString("mobile"));
			address.setPhone(rs.getString("phone"));
			addresses.add(address);
		}
		return addresses;
	}
	
	private static final String findById=
		"select * from d_receive_address where id=?";
	public Address findById(int id) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(findById);
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		Address address=null;
		if(rs.next()){
			address=new Address();
			address.setId(rs.getInt("id"));
			address.setUserId(rs.getInt("user_id"));
			address.setReceiveName(rs.getString("receive_name"));
			address.setFullAddress(rs.getString("full_address"));
			address.setPostalCode(rs.getString("postal_code"));
			address.setMobile(rs.getString("mobile"));
			address.setPhone(rs.getString("phone"));
		}
		return address;
	}
	
	
	public static void main(String[] args) throws SQLException {
		AddressDao dao=new JDBCAddressDao();
//		AddressDAO dao=(AddressDAO) Factory.getInstance("AddressDAO");                                                        
//		Address address=new Address();
//		address.setUserId(2);
//		address.setReceiveName("zhangsan");
//		address.setFullAddress("beijing");
//		address.setPostalCode("100000");
//		address.setMobile("13800000000");
//		address.setPhone("029-88888888");
//		dao.save(address);
//		System.out.println(address.getId());
//		List<Address> list=dao.findAddressByUserId(1);
//		for(Address address:list){
//			System.out.println(address.getFullAddress());
//		}
		Address address=dao.findById(3);
		System.out.println(address.getFullAddress());
	}
	
	
}
