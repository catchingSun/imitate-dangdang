package com.tarena.dang.dao;

import java.sql.SQLException;

import com.tarena.dang.entity.User;

public interface UserDao {
	public User findByLogin(String email, String pwd);
	public void doUpdateIpTime(Integer id, Long lastLoginTime, String IP);
	void save(User user) throws SQLException;
	User checkEmailVerifyCode(int id, String verifyCode) throws SQLException;
	public User findByEmail(String email)throws SQLException;
}
