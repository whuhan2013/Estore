package com.zj.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zj.dao.OrderDao;
import com.zj.dao.ProdDao;
import com.zj.dao.UserDao;
import com.zj.domain.Order;
import com.zj.domain.OrderItem;
import com.zj.domain.OrderListForm;
import com.zj.domain.Product;
import com.zj.domain.SaleInfo;
import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.util.TransactionManager;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao=BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao=BasicFactory.getFactory().getDao(ProdDao.class);
	UserDao userDao=BasicFactory.getFactory().getDao(UserDao.class);
	@Override
	public void addOrder(Order order) {
		// TODO 自动生成的方法存根
		try
		{
		
		//生成订单
		orderDao.addOrder(order);
		//生成订单项
		for(OrderItem item:order.getList())
		{
			orderDao.addOrderItem(item);
			//扣除商品数量
			prodDao.delpnum(item.getProduct_id(),item.getBuynum());
		}
		//扣除商品数量
		
		}catch(Exception e)
		{
			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
		
	}
	@Override
	public List<OrderListForm> findOrders(int user_id) {
		// TODO 自动生成的方法存根
		//根据用户ID查询用户所有的订单
		try
		{
		List<Order> list= orderDao.findOrderByUserId(user_id);
		List<OrderListForm> olflist=new ArrayList<OrderListForm>();
		for(Order order:list)
		{
			OrderListForm olf=new OrderListForm();
			//olf.setId(order.getId());
			//设置订单信息
			BeanUtils.copyProperties(olf, order);
			//设置用户名
			User user=userDao.findUserById(order.getUser_id());
			olf.setUsername(user.getUsername());
			//设置商品信息
			//查询当前订单所有订单项
			List<OrderItem> itemList=orderDao.findOrderItem(order.getId());
			//遍历所有订单项，获取商品Id，查找对应商品，存入List
			List<Product> prodList=new ArrayList<Product>();
			Map<Product, Integer> map=new LinkedHashMap<Product, Integer>();
			for(OrderItem item:itemList)
			{
				String Prod_id= item.getProduct_id();
				Product product=prodDao.findProdById(Prod_id);
				int  num=item.getBuynum();
				map.put(product, num);
				
			}
			olf.setProdMap(map);
			olflist.add(olf);
			
		}
		//遍历订单，生成orderLsitForm对象,存入list;
		
		return olflist;
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	@Override
	public void delOrderByID(String id) {
		// TODO 自动生成的方法存根
		//根据ID查询出订单所有订单项
		List<OrderItem> list=orderDao.findOrderItem(id);
		//遍历订单项，将对应商品库存加回去
		for(OrderItem item:list)
		{
			prodDao.addpnum(item.getProduct_id(),item.getBuynum());
			
		}
		//删除订单项
		orderDao.delOrderItem(id);
		//删除订单
		orderDao.delOrder(id);
		
	}
	@Override
	public Order findOrderById(String p2_Order) {
		// TODO 自动生成的方法存根
		return orderDao.findOrderById(p2_Order);
	}
	@Override
	public void changePayState(String r6_Order, int i) {
		// TODO 自动生成的方法存根
		orderDao.changePayState(r6_Order,i);
		
	}
	@Override
	public List<SaleInfo> saleList() {
		// TODO 自动生成的方法存根
		return orderDao.saleList();
	}

}
