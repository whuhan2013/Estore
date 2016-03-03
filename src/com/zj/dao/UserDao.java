package com.zj.dao;

import java.sql.Connection;
import java.util.List;

import com.zj.domain.User;

public interface UserDao extends Dao{

	/**
	 * �����û��������û�
	 * @param username
	 * @return ���ҵ����û�,�Ҳ�������NULL
	 */
	User findUserByName(String username);
	
    /**
     * ��ӿͻ��ķ���
     * @param user
     */
	void addUser(User user);

	/**
	 * ���ݼ���������û�
	 * @param findU
	 * @return
	 */
	List<User> find(User findU);

	/**
	 * ɾ���û�����
	 * @param id
	 */
	void delete(int id);

	/**
	 * �����û��ķ���
	 * @param id
	 */
	void updateState(int id);

	/**
	 * ���ݼ���������û�
	 * @param activecode
	 * @return
	 */
	User findUserByActivecode(String activecode);

	/**
	 * �����û�������������û�
	 * @param username
	 * @param password
	 * @return
	 */
	User finUserByNameAndPsw(String username, String password);

	/**
	 * ͨ��Id�����û�
	 * @param user_id
	 * @return
	 */
	User findUserById(int user_id);

}
