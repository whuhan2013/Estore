package com.zj.dao;

import java.util.List;

import com.zj.domain.Order;
import com.zj.domain.OrderItem;
import com.zj.domain.SaleInfo;

public interface OrderDao extends Dao{

	/**
	 * �ڶ������в����¼�ķ���
	 * @param order
	 */
	void addOrder(Order order);

	/**
	 * �ڶ�������в����¼
	 * @param item
	 */
	void addOrderItem(OrderItem item);

	/**
	 * ��ѯָ���û������ж���
	 * @param user_id
	 * @return
	 */
	List<Order> findOrderByUserId(int user_id);

	/**
	 * ��ѯָ�����������ж�����
	 * @param id
	 * @return
	 */
	List<OrderItem> findOrderItem(String id);

	/**
	 * ɾ���������
	 * @param id
	 */
	void delOrderItem(String id);

	/**
	 * ɾ������ID
	 * @param id
	 */
	void delOrder(String id);

	/**
	 * ͨ��ID���Ҷ���
	 * @param p2_Order
	 * @return
	 */
	Order findOrderById(String p2_Order);

	/**
	 * �ı�֧��״̬
	 * @param r6_Order
	 * @param i
	 */
	void changePayState(String r6_Order, int i);

	/**
	 * ��ѯ���۰񵥵ķ���
	 * @return
	 */
	List<SaleInfo> saleList();

}
