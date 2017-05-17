package com.tarena.dang.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.BookDao;
import com.tarena.dang.entity.Book;
import com.tarena.dang.entity.Product;
import com.tarena.dang.util.DBUtil;

public class JDBCBookDao implements BookDao {

	@Override
	public List<Product> findByCatId(int catId, int page, int size)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select dp.* , db.* from d_category_product dcp join d_product dp on (dcp.product_id = dp.id) join d_book db on(dp.id = db.id) where dcp.cat_id = ? limit ?,?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, catId);
		int begin = (page - 1) * size;
		pstmt.setInt(2, begin);
		pstmt.setInt(3, size);
		ResultSet rs = pstmt.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while(rs.next()){
			Book pro = new Book();
			pro.setId(rs.getInt("id"));
			pro.setProductPic(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPublishTime(rs.getLong("publish_time"));
			pro.setWordNumber(rs.getString("word_number"));
			pro.setWhichEdtion(rs.getString("which_edtion"));
			pro.setTotalPage(rs.getString("total_page"));
			pro.setPrintTime(rs.getInt("print_time"));
			pro.setPrintNumber(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthorSummary(rs.getString("author_summary"));
			pro.setCatalogue(rs.getString("catalogue"));
			list.add(pro);
		}
		return list;
	}

	@Override
	public int getTotalPage(int catId, int size) throws Exception {
		// TODO Auto-generated method stuby
		String sql = "select count(*) from d_category_product dcp join d_product dp on (dcp.product_id = dp.id) join d_book db on (dp.id = db.id) where dcp.cat_id = ?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, catId);
		int result = 0;
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);
		}
		if(result % size == 0){
			return result/size;
		}else{
			return result/size + 1; 
		}
	}

	@Override
	public List<Product> findAll() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from d_product dp join d_book db on(dp.id = db.id ) where dp.has_deleted = 0";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while(rs.next()){
			Book pro = new Book();
			pro.setId(rs.getInt("id"));
			pro.setProductPic(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPublishTime(rs.getLong("publish_time"));
			pro.setWordNumber(rs.getString("word_number"));
			pro.setWhichEdtion(rs.getString("which_edtion"));
			pro.setTotalPage(rs.getString("total_page"));
			pro.setPrintTime(rs.getInt("print_time"));
			pro.setPrintNumber(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthorSummary(rs.getString("author_summary"));
			pro.setCatalogue(rs.getString("catalogue"));
			list.add(pro);
		}
		return list;
	}

	@Override
	public Product findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		String findById = "select * from d_product dp join d_book db on(dp.id=db.id) where dp.id=?";
		PreparedStatement pst = DBUtil.getConnection().prepareStatement(
				findById);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		Book pro = null;
		if (rs.next()) {
			System.out.println("jdbcppppppppppppppppp");
			pro = new Book();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			// 设置db.*的信息-->d_book表字段
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPublishTime(rs.getLong("publish_time"));
			pro.setWordNumber(rs.getString("word_number"));
			pro.setWhichEdtion(rs.getString("which_edtion"));
			pro.setTotalPage(rs.getString("total_page"));
			pro.setPrintTime(rs.getInt("print_time"));
			pro.setPrintNumber(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthorSummary(rs.getString("author_summary"));
			pro.setCatalogue(rs.getString("catalogue"));
		}
		return pro;
	}

}
