package com.zj.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zj.domain.User;
import com.zj.util.DaoUtils;
import com.zj.util.TransactionManager;

public class UserDaoImpl implements UserDao {

	@Override
	public User findUserByName(String username) {
		// TODO 自动生成的方法存根
		String sql="select * from users where username=?";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), username);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void addUser(User user) {
		// TODO 自动生成的方法存根
		String sql="insert into users values(null,?,?,?,?,?,?,?,null)";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
			 runner.update(sql,  user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getRole(),user.getState(),user.getActivecode());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<User> find(User findU) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO 自动生成的方法存根
		String sql = "delete from users where id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		
	}

	@Override
	public void updateState(int id) {
		// TODO 自动生成的方法存根
		String sql = "update users set state = 1 where id=?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		
	}

	@Override
	public User findUserByActivecode(String activecode) {
		// TODO 自动生成的方法存根
		String sql = "select * from users where activecode = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),activecode);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User finUserByNameAndPsw(String username, String password) {
		// TODO 自动生成的方法存根
		String sql = "select * from users where username = ? and password = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class) ,username ,password);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserById(int user_id) {
		// TODO 自动生成的方法存根
		String sql = "select * from users where id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),user_id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
