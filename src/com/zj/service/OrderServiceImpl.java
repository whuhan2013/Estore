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
		// TODO �Զ����ɵķ������
		try
		{
		
		//���ɶ���
		orderDao.addOrder(order);
		//���ɶ�����
		for(OrderItem item:order.getList())
		{
			orderDao.addOrderItem(item);
			//�۳���Ʒ����
			prodDao.delpnum(item.getProduct_id(),item.getBuynum());
		}
		//�۳���Ʒ����
		
		}catch(Exception e)
		{
			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
		
	}
	@Override
	public List<OrderListForm> findOrders(int user_id) {
		// TODO �Զ����ɵķ������
		//�����û�ID��ѯ�û����еĶ���
		try
		{
		List<Order> list= orderDao.findOrderByUserId(user_id);
		List<OrderListForm> olflist=new ArrayList<OrderListForm>();
		for(Order order:list)
		{
			OrderListForm olf=new OrderListForm();
			//olf.setId(order.getId());
			//���ö�����Ϣ
			BeanUtils.copyProperties(olf, order);
			//�����û���
			User user=userDao.findUserById(order.getUser_id());
			olf.setUsername(user.getUsername());
			//������Ʒ��Ϣ
			//��ѯ��ǰ�������ж�����
			List<OrderItem> itemList=orderDao.findOrderItem(order.getId());
			//�������ж������ȡ��ƷId�����Ҷ�Ӧ��Ʒ������List
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
		//��������������orderLsitForm����,����list;
		
		return olflist;
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	@Override
	public void delOrderByID(String id) {
		// TODO �Զ����ɵķ������
		//����ID��ѯ���������ж�����
		List<OrderItem> list=orderDao.findOrderItem(id);
		//�������������Ӧ��Ʒ���ӻ�ȥ
		for(OrderItem item:list)
		{
			prodDao.addpnum(item.getProduct_id(),item.getBuynum());
			
		}
		//ɾ��������
		orderDao.delOrderItem(id);
		//ɾ������
		orderDao.delOrder(id);
		
	}
	@Override
	public Order findOrderById(String p2_Order) {
		// TODO �Զ����ɵķ������
		return orderDao.findOrderById(p2_Order);
	}
	@Override
	public void changePayState(String r6_Order, int i) {
		// TODO �Զ����ɵķ������
		orderDao.changePayState(r6_Order,i);
		
	}
	@Override
	public List<SaleInfo> saleList() {
		// TODO �Զ����ɵķ������
		return orderDao.saleList();
	}

}
