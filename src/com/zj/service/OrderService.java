package com.zj.service;

import java.util.List;

import com.zj.annotation.Tran;
import com.zj.domain.Order;
import com.zj.domain.OrderListForm;
import com.zj.domain.SaleInfo;

public interface OrderService extends Service{

	/**
	 * 增加订单的方法
	 * @param order
	 */
	@Tran
	void addOrder(Order order);

	/**
	 * 查询指定用户所有订单的方法
	 * @param user_id
	 */
	List<OrderListForm> findOrders(int user_id);

	/**
	 * 根据订单编号删除订单的方法
	 * @param id
	 */
	@Tran
	void delOrderByID(String id);

	/**
	 * 根据订单查询ID
	 * @param p2_Order
	 * @return
	 */
	Order findOrderById(String p2_Order);

	/**
	 * 改变支付状态
	 * @param r6_Order
	 * @param i
	 */
	void changePayState(String r6_Order, int i);

	/**
	 * 查询销售榜单的方法
	 * @return
	 */
	List<SaleInfo> saleList();

}
