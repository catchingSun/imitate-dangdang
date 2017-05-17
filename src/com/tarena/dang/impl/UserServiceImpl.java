//李静
package com.tarena.dang.impl;
import java.util.UUID;

import com.tarena.dang.dao.UserDao;
import com.tarena.dang.entity.User;
import com.tarena.dang.util.EncryptUtil;

public class UserServiceImpl implements UserService{
	@Override
	public void regist(User user) throws Exception{
		String pwd=EncryptUtil.md5Encrypt(user.getPassword());
		user.setPassword(pwd);
		user.setUserIntegral(0);//等级
		user.setEmailVerify(false);
		user.setLastLoginTime(System.currentTimeMillis());
		String emailVerifyCode=UUID.randomUUID().toString();
		user.setEmailVerifyCode(emailVerifyCode);
		UserDao userDao=new JDBCUserDao();
		userDao.save(user);
		System.out.println("给"+user.getEmail()+"发送验证码"+emailVerifyCode+"-"+user.getId());
	}
	
	@Override
	public User checkEmialVarifyCode(String code) throws Exception{
		User user=null;
		int index=code.lastIndexOf("-");
		String verifyCode=code.substring(0,index);
		int id=Integer.parseInt(code.substring(index+1,code.length()));
		UserDao dao = new JDBCUserDao();
		user=dao.checkEmailVerifyCode(id,verifyCode);
		return user;
	}
}