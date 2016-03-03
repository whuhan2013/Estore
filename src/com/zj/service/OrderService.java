package com.zj.service;

import java.util.List;

import com.zj.annotation.Tran;
import com.zj.domain.Order;
import com.zj.domain.OrderListForm;
import com.zj.domain.SaleInfo;

public interface OrderService extends Service{

	/**
	 * ���Ӷ����ķ���
	 * @param order
	 */
	@Tran
	void addOrder(Order order);

	/**
	 * ��ѯָ���û����ж����ķ���
	 * @param user_id
	 */
	List<OrderListForm> findOrders(int user_id);

	/**
	 * ���ݶ������ɾ�������ķ���
	 * @param id
	 */
	@Tran
	void delOrderByID(String id);

	/**
	 * ���ݶ�����ѯID
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
