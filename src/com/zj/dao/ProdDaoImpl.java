package com.zj.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zj.domain.Product;
import com.zj.util.DaoUtils;
import com.zj.util.TransactionManager;

public class ProdDaoImpl implements ProdDao {

	@Override
	public void addProd(Product prod) {
		// TODO 自动生成的方法存根
		String sql = "insert into products values (?,?,?,?,?,?,?)";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,prod.getId(),prod.getName(),prod.getPrice(),prod.getCategory(),prod.getPnum(),prod.getImgurl(),prod.getDescription());
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Product> findAllProd() {
		// TODO 自动生成的方法存根
		String sql="select * from products";
		
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Product>(Product.class));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findProdById(String id) {
		// TODO 自动生成的方法存根
String sql="select * from products where id=?";
		
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class),id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delpnum(String product_id, int buynum) {
		// TODO 自动生成的方法存根
		String sql = "update products set pnum=pnum-? where id=? and pnum-?>=0";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
			int count=runner.update(sql,buynum,product_id,buynum);
			if(count<=0)
			{
				throw new RuntimeException("库存不足");
			}
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public void addpnum(String product_id, int buynum) {
		// TODO 自动生成的方法存根
		String sql = "update products set pnum=pnum+? where id=? ";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
			runner.update(sql,buynum,product_id);
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getCountRow() {
		// TODO 自动生成的方法存根
		String sql = "select count(*) from products";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Product> getProductByPage(int i, int rowperpage) {
		// TODO 自动生成的方法存根
		String sql = "select * from products limit ?,?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Product>(Product.class),i,rowperpage);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
