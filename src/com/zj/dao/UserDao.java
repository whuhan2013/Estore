package com.zj.dao;

import java.sql.Connection;
import java.util.List;

import com.zj.domain.User;

public interface UserDao extends Dao{

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return 查找到的用户,找不到返回NULL
	 */
	User findUserByName(String username);
	
    /**
     * 添加客户的方法
     * @param user
     */
	void addUser(User user);

	/**
	 * 根据激活码查找用户
	 * @param findU
	 * @return
	 */
	List<User> find(User findU);

	/**
	 * 删除用户方法
	 * @param id
	 */
	void delete(int id);

	/**
	 * 激活用户的方法
	 * @param id
	 */
	void updateState(int id);

	/**
	 * 根据激活码查找用户
	 * @param activecode
	 * @return
	 */
	User findUserByActivecode(String activecode);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	User finUserByNameAndPsw(String username, String password);

	/**
	 * 通过Id查找用户
	 * @param user_id
	 * @return
	 */
	User findUserById(int user_id);

}
