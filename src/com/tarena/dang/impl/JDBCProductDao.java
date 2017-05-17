package com.tarena.dang.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.entity.Book;
import com.tarena.dang.entity.Product;
import com.tarena.dang.util.DBUtil;

public class JDBCProductDao implements ProductDao {

	@Override
	public List<Product> findNewProduct(int size) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from d_product where has_deleted "
				+ "= 0 order by add_time desc limit ?";

		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, size);
		ResultSet rs = pstmt.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Product pro = new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	@Override
	public List<Product> findNewHotProduct(int size, int month)
			throws SQLException {
		String sql = "select dp.*, sum(di.product_num) as cnt from d_item di join d_product dp"
				+ " on (di.product_id = dp.id) join d_order dod on (di.order_id = dod.id)"
				+ " where dod.order_time>? group by dp.id order by cnt desc limit ?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		long time = System.currentTimeMillis() - (long) month * 30 * 24 * 60
				* 60 * 1000;
		pstmt.setLong(1, time);
		pstmt.setInt(2, size);
		ResultSet rs = pstmt.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			System.out.println("--------------");
			Product pro = new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	@Override
	public List<Product> findHotProduct(int size) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select dp.*, sum(di.product_num) as cnt from "
				+ "d_item di join d_product dp on (di.product_id = dp.id) "
				+ "group by dp.id order by cnt desc limit ?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, size);
		ResultSet rs = pstmt.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			System.out.println("--------------");
			Product pro = new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	public static void main(String[] args) {
		JDBCProductDao j = new JDBCProductDao();
		try {
			j.findHotProduct(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Product findById(int pid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from d_product where id = ?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, pid);
		ResultSet rs = pstmt.executeQuery();
		Product pro = null;
		if (rs.next()) {
			pro = new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
		}
		return pro;
	}

}
