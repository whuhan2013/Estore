package com.zj.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.dbutils.DbUtils;

import com.zj.dao.UserDao;
import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.util.DaoUtils;

public class UserServiceImpl implements UserService {
	private UserDao dao=BasicFactory.getFactory().getDao(UserDao.class);

	@Override
	public void regist(User user) {
		// TODO �Զ����ɵķ������
		
		try {
			
			
		
		//У���û���
		if(dao.findUserByName(user.getUsername())!=null)
		{
			throw new RuntimeException("�û����Ѿ�����");
		}
		//����DAO�еķ�������û�
		user.setRole("user");
		user.setState(0);
		user.setActivecode(UUID.randomUUID().toString());
		dao.addUser(user);
		//���ͼ����ʼ�
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "localhost");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.debug", "true");
		Session session=Session.getInstance(prop);
		
		Message msg=new MimeMessage(session);
		msg.setFrom(new InternetAddress("aa@zj.com"));
		msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
		msg.setSubject(user.getUsername()+",����estore�ļ����ʼ�");
		msg.setText(user.getUsername()+"����������Ӽ����û���������ܵ���븴�Ƶ��������ַ�����ʣ�localhost/Estore/ActiveServlet?activecode="+user.getActivecode());
		
		Transport trans=session.getTransport();
		trans.connect("aa","123");
		trans.sendMessage(msg, msg.getAllRecipients());
		
		//conn.commit();
		}catch(Exception e)
		{
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
		
	}

	@Override
	public User active(String activecode) {
		// TODO �Զ����ɵķ������
		User findU=new User();
		findU.setActivecode(activecode);
		User user=dao.findUserByActivecode(activecode);
		if(user==null)
		{
			throw new RuntimeException("�����벻����,�������ļ�����");
		}
		//����û��Ѿ�������������ظ�����
		if(user.getState()!=0)
		{
			throw new RuntimeException("�û��Ѿ������Ҫ�ظ������ֱ�ӵ�½");
		}
		//�û�û�м�����������Ѿ�����
		if(System.currentTimeMillis() - user.getUpdatetime().getTime()>1000*3600*24)
		{
			dao.delete(user.getId());
			throw new RuntimeException("�����볬ʱ�����û����ϣ�������ע��");
			
		}
		dao.updateState(user.getId());
		user.setState(1);
		return user;
	}

	@Override
	public User getUserByNameAndPSW(String username, String password) {
		// TODO �Զ����ɵķ������
		
		return dao.finUserByNameAndPsw(username,password);
	}

	@Override
	public boolean hasName(String username) {
		// TODO �Զ����ɵķ������
		return dao.findUserByName(username)!=null;
		
	}

}
