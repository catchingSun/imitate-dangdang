package com.tarena.dang.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public final class DBUtil {
	private static DataSource ds;
	private static ThreadLocal<Connection> connLocal=new ThreadLocal<Connection>(); 
	static{
		Properties props=new Properties();
		try {
			props.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName("com.mysql.jdbc.Driver");
			ds=BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws SQLException{
		//先尝试从local中获取
		Connection conn=connLocal.get();
		//获取不到,说明当前线程前面没使用过
		if(conn==null){
			//从连接池中获取一个,放入local
			conn=ds.getConnection();
			connLocal.set(conn);
		}
		return conn;
	}
	public static void closeConnection() throws SQLException{
		//先尝试从local中获取
		Connection conn=connLocal.get();
		//清空local
		connLocal.set(null);
		if(conn!=null&&!conn.isClosed()){
			conn.close();
		}
	}
	public static void main(String[] args) throws SQLException {
		Connection conn=getConnection();
		System.out.println(conn.hashCode());
		Connection conn1=getConnection();
		System.out.println(conn1.hashCode());
		closeConnection();
	}
}
