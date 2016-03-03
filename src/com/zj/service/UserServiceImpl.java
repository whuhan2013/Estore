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
		// TODO 自动生成的方法存根
		
		try {
			
			
		
		//校验用户名
		if(dao.findUserByName(user.getUsername())!=null)
		{
			throw new RuntimeException("用户名已经存在");
		}
		//调用DAO中的方法添加用户
		user.setRole("user");
		user.setState(0);
		user.setActivecode(UUID.randomUUID().toString());
		dao.addUser(user);
		//发送激活邮件
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "localhost");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.debug", "true");
		Session session=Session.getInstance(prop);
		
		Message msg=new MimeMessage(session);
		msg.setFrom(new InternetAddress("aa@zj.com"));
		msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
		msg.setSubject(user.getUsername()+",来自estore的激活邮件");
		msg.setText(user.getUsername()+"点击如下链接激活用户，如果不能点击请复制到浏览器地址栏访问：localhost/Estore/ActiveServlet?activecode="+user.getActivecode());
		
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
		// TODO 自动生成的方法存根
		User findU=new User();
		findU.setActivecode(activecode);
		User user=dao.findUserByActivecode(activecode);
		if(user==null)
		{
			throw new RuntimeException("激活码不存在,请检查您的激活码");
		}
		//如果用户已经激活过，不能重复激活
		if(user.getState()!=0)
		{
			throw new RuntimeException("用户已经激活，不要重复激活，请直接登陆");
		}
		//用户没有激活，但激活码已经激活
		if(System.currentTimeMillis() - user.getUpdatetime().getTime()>1000*3600*24)
		{
			dao.delete(user.getId());
			throw new RuntimeException("激活码超时，此用户作废，请重新注册");
			
		}
		dao.updateState(user.getId());
		user.setState(1);
		return user;
	}

	@Override
	public User getUserByNameAndPSW(String username, String password) {
		// TODO 自动生成的方法存根
		
		return dao.finUserByNameAndPsw(username,password);
	}

	@Override
	public boolean hasName(String username) {
		// TODO 自动生成的方法存根
		return dao.findUserByName(username)!=null;
		
	}

}
