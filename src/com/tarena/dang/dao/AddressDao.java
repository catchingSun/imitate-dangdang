package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Address;


public interface AddressDao {
	public void save(Address address)throws SQLException;
	public List<Address> findAddressByUserId(int userId)throws SQLException;
	public Address findById(int id)throws SQLException;
}


