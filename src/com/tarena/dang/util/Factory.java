package com.tarena.dang.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * 根据Dao接口或者Service接口名称获得相应的实现类对象
 *
 */
public class Factory {
	/*private static Properties config=new Properties();
	static{
		InputStream in=Factory.class.getClassLoader().getResourceAsStream("daoAndservice.properties");
		try {
			config.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static Object getInstance(String interfaceName){
		Object object=null;
		String className=config.getProperty(interfaceName);
		//反射编程获得该类实例
		try {
			Class c=Class.forName(className);
			object=c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	public static void main(String[] args) {
		BookDAO bookDAO=(BookDAO) Factory.getInstance("BookDAO");
		System.out.println(bookDAO.getClass().getName());
		UserDAO userDAO=(UserDAO) Factory.getInstance("UserDAO");
		System.out.println(userDAO.getClass().getName());
		
	}*/
}
