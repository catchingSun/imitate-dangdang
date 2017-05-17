package com.tarena.dang.impl;

import com.tarena.dang.entity.User;

public interface UserService {

	User checkEmialVarifyCode(String code) throws Exception;
	void regist(User user) throws Exception;

}
