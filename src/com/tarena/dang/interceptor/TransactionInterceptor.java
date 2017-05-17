package com.tarena.dang.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.dang.util.DBUtil;

public class TransactionInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6063859009532689160L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//开启事务控制
		System.out.println("开启事务控制");
		DBUtil.getConnection().setAutoCommit(false);
		try {
			String result=invocation.invoke();//执行后续的拦截器或Action
			//结束事务控制
			if(!DBUtil.getConnection().getAutoCommit()){
				DBUtil.getConnection().commit();
				System.out.println("提交事务");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.getConnection().rollback();
			System.out.println("回滚事务");
			throw e;//将异常继续抛出,交给Struts框架处理
		}finally{
			DBUtil.closeConnection();
			System.out.println("关闭");
		}
	}
}
