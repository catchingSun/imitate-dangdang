package com.tarena.dang.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.CategoryDao;
import com.tarena.dang.entity.Category;
import com.tarena.dang.util.DBUtil;

public class JDBCCategoryDao implements CategoryDao {

	@Override
	public List<Category> findByParentId(Integer pid) {
		// TODO Auto-generated method stub
		List<Category> cats = new ArrayList<Category>();
		try {
			String sql = "select dc.* , count(dcp.product_id) as cnt from d_category dc left outer join d_category_product dcp on(dc.id = dcp.cat_id) where parent_id = ? group by dc.id";
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Category ca = new Category();
				Integer id = rs.getInt(1);
				Integer turn = rs.getInt("turn");
				String enName = rs.getString("en_name");
				String name = rs.getString("name");
				String des = rs.getString("description");
				Integer parentId = rs.getInt("parent_id");
				ca.setId(id);
				ca.setTurn(turn);
				ca.setEnName(enName);
				ca.setDescription(des);
				ca.setParentId(parentId);
				cats.add(ca);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return cats;
	}

	@Override
	public String getCategoryNameById(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select name from d_category where id=?";
		String name = null;
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			name = rs.getString(1);
		}
		return name;
	}

	@Override
	public List<Category> findAll() throws Exception{
		// TODO Auto-generated method stub
		String sql = "select * from d_category";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		List<Category> list = new ArrayList<Category>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Category cat = new Category();
			cat.setId(rs.getInt("id"));
			cat.setName(rs.getString("name"));
			cat.setEnName(rs.getString("en_name"));
			cat.setTurn(rs.getInt("turn"));
			cat.setDescription(rs.getString("description"));
			cat.setParentId(rs.getInt("parent_id"));
			list.add(cat);
		}
		return list;
	}

}
