package com.zj.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zj.domain.Order;
import com.zj.domain.OrderItem;
import com.zj.domain.SaleInfo;
import com.zj.util.DaoUtils;
import com.zj.util.TransactionManager;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) {
		// TODO 自动生成的方法存根
		String sql = "insert into orders values (?,?,?,?,null,?)";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
			runner.update(sql,order.getId(),order.getMoney(),order.getReceiverinfo(),order.getPaystate(),order.getUser_id());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addOrderItem(OrderItem item) {
		// TODO 自动生成的方法存根
		String sql = "insert into orderitem values (?,?,?)";
		try {
			QueryRunner runner=new QueryRunner(TransactionManager.getSource());
			runner.update(sql,item.getOrder_id(),item.getProduct_id(),item.getBuynum());
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Order> findOrderByUserId(int user_id) {
		// TODO 自动生成的方法存根
		String sql = "select * from orders where user_id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),user_id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderItem> findOrderItem(String id) {
		// TODO 自动生成的方法存根
		String sql = "select * from orderitem where order_id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delOrderItem(String id) {
		// TODO 自动生成的方法存根
		String sql="delete from orderitem where order_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delOrder(String id) {
		// TODO 自动生成的方法存根
		String sql="delete from orders where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public Order findOrderById(String p2_Order) {
		// TODO 自动生成的方法存根
		String sql = "select * from orders where id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Order>(Order.class),p2_Order);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void changePayState(String r6_Order, int i) {
		// TODO 自动生成的方法存根
		String sql = "update orders set paystate = ? where id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,i,r6_Order);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<SaleInfo> saleList() {
		// TODO 自动生成的方法存根
		String sql = 
				" select products.id prod_id,products.name prod_name,sum(orderitem.buynum ) sale_num"+
				" from orders ,orderitem ,products "+
				" where "+
				" orders.id=orderitem.order_id "+
				" and "+
				" orderitem.product_id=products.id"+
				" and orders.paystate = 1"+
				" group by products.id"+
				" order by sale_num desc";
		
		try{
			QueryRunner runner  = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<SaleInfo>(SaleInfo.class));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
