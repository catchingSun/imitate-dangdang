package com.tarena.dang.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.dang.entity.User;

public class CheckLoginInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082792134907192335L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac=invocation.getInvocationContext();
		Map<String,Object> session=ac.getSession();
		User user=(User)session.get("user");
		if(user==null){
			//未登录,转到login标识的页面
			return "login";
		}else{
			return invocation.invoke();
		}
	}

}
