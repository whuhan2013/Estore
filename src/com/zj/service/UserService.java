package com.zj.service;

import com.zj.annotation.Tran;
import com.zj.domain.User;

public interface UserService extends Service{

	/**
	 * 注册用户的方法
	 *
	 * @param user
	 * 
	 */
	@Tran
	void regist(User user);

	/**
	 * 激活用户方法
	 * @param activecode
	 */
	User active(String activecode);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 */
	User getUserByNameAndPSW(String username, String password);

	/**
	 * 检验用户名是否已经存在
	 * @param username
	 * @return
	 */
	boolean hasName(String username);

}
