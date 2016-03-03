package com.zj.dao;

import java.util.List;

import com.zj.domain.Order;
import com.zj.domain.OrderItem;
import com.zj.domain.SaleInfo;

public interface OrderDao extends Dao{

	/**
	 * 在订单表中插入记录的方法
	 * @param order
	 */
	void addOrder(Order order);

	/**
	 * 在订单项表中插入记录
	 * @param item
	 */
	void addOrderItem(OrderItem item);

	/**
	 * 查询指定用户的所有订单
	 * @param user_id
	 * @return
	 */
	List<Order> findOrderByUserId(int user_id);

	/**
	 * 查询指定订单的所有订单项
	 * @param id
	 * @return
	 */
	List<OrderItem> findOrderItem(String id);

	/**
	 * 删除订单项方法
	 * @param id
	 */
	void delOrderItem(String id);

	/**
	 * 删除订单ID
	 * @param id
	 */
	void delOrder(String id);

	/**
	 * 通过ID查找订单
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
