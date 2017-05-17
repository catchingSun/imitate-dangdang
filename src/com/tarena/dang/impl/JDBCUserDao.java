package com.tarena.dang.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tarena.dang.dao.UserDao;
import com.tarena.dang.entity.User;
import com.tarena.dang.util.DBUtil;

public class JDBCUserDao implements UserDao {

	@Override
	public User findByLogin(String email, String pwd) {
		// TODO Auto-generated method stub

		User user = null;

		try {
			// Connection conn = new ConnectMysql().connectMysql();
			String sql = "select * from d_user where email = ? and password = ?";

			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				Integer id = rs.getInt("id");
				String email1 = rs.getString("email");
				String nickName = rs.getString("nickname");
				// String pwd = rs.getString("password");
				String password = rs.getString("password");
				Boolean isEmailVerify = rs.getBoolean("is_email_verify");
				user.setId(id);
				user.setPassword(password);
				user.setEmail(email1);
				user.setNickname(nickName);
				user.setEmailVerify(isEmailVerify);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return user;
	}

	public static void main(String[] args) {
		JDBCUserDao dao = new JDBCUserDao();

		User user = dao.findByLogin("742400198@qq.com",
				"4QrcOUm6Wau+VuBX8g+IPg==");

		System.out.println(user.getEmail());
		System.out.println("-" + user.getPassword());
	}

	@Override
	public void doUpdateIpTime(Integer id, Long lastLoginTime, String IP) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "update d_user set last_login_time = ? , last_login_ip = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, lastLoginTime);
			pstmt.setString(2, IP);
			pstmt.setInt(3, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save(User user) throws SQLException {
		String save = "insert into d_user(email, nickname, password"
				+ ",user_integral, is_email_verify, email_verify_code, last_login_time,"
				+ "last_login_ip) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pst = DBUtil.getConnection().prepareStatement(save,
				Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, user.getEmail());
		pst.setString(2, user.getNickname());
		pst.setString(3, user.getPassword());
		pst.setInt(4, user.getUserIntegral());
		String emailVerify = user.getEmailVerify() ? "Y" : "N";
		pst.setString(5, emailVerify);
		pst.setString(6, user.getEmailVerifyCode());
		pst.setLong(7, user.getLastLoginTime());
		pst.setString(8, user.getLastLoginIp());
		pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		user.setId(id);
	}

	@Override
	public User checkEmailVerifyCode(int id, String verifyCode)
			throws SQLException {
		User user = null;
		String find_By_IdAndVerifyCode = "select * from d_user where id=? "
				+ "and email_verify_code=?";

		PreparedStatement pst = DBUtil.getConnection().prepareStatement(
				find_By_IdAndVerifyCode);
		pst.setInt(1, id);
		pst.setString(2, verifyCode);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setEmailVerify(true);
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
			String update_EmailVerify = "update d_user set is_email_verify=? where id=?";

			pst = DBUtil.getConnection().prepareStatement(update_EmailVerify);
			pst.setString(1, "Y");
			pst.setInt(2, id);
			pst.executeUpdate();
		}
		return user;
	}

	@Override
	public User findByEmail(String email) throws SQLException {
		User user = null;
		String sql = "select * from d_user where email = ?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setUserIntegral(rs.getInt("user_integral"));
			Boolean emailVerify = rs.getString("is_email_verify").equals("Y") ? true
					: false;
			user.setEmailVerify(emailVerify);
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
		}
		return user;
	}

}
