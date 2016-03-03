package com.zj.service;

import com.zj.annotation.Tran;
import com.zj.domain.User;

public interface UserService extends Service{

	/**
	 * ע���û��ķ���
	 *
	 * @param user
	 * 
	 */
	@Tran
	void regist(User user);

	/**
	 * �����û�����
	 * @param activecode
	 */
	User active(String activecode);

	/**
	 * �����û�������������û�
	 * @param username
	 * @param password
	 */
	User getUserByNameAndPSW(String username, String password);

	/**
	 * �����û����Ƿ��Ѿ�����
	 * @param username
	 * @return
	 */
	boolean hasName(String username);

}
