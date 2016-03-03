package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.zj.domain.Order;
import com.zj.domain.OrderItem;
import com.zj.domain.Product;
import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.service.OrderService;

public class AddOrderServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService service=BasicFactory.getFactory().getService(OrderService.class);

		try
		{
		//将订单信息存入orderbean
		Order order=new Order();
		
		//订单编号
		order.setId(UUID.randomUUID().toString());
		
		//支付状态
		order.setPaystate(0);
		//收货地址
		BeanUtils.populate(order, request.getParameterMap());
		
		Map<Product, Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		//用户金额
		//将订单项信息存入ORDER中
		double money=0;
		List<OrderItem> list=new ArrayList<OrderItem>();
		for(Map.Entry<Product, Integer> entry:cartmap.entrySet())
		{
			money+=entry.getKey().getPrice()*entry.getValue();
			OrderItem item=new OrderItem();
			item.setOrder_id(order.getId());
			item.setProduct_id(entry.getKey().getId());
			item.setBuynum(entry.getValue());
			list.add(item);
		}
		order.setMoney(money);
		order.setList(list);
		//用户ID
		User user=(User) request.getSession().getAttribute("user");
		order.setUser_id(user.getId());
		
		//调用service中添加订单的方法
		service.addOrder(order);
		//清空购物车
		cartmap.clear();
		//重定向回主页
		response.getWriter().write("订单生成成功，请前去支付");
		response.setHeader("Refresh", "3;url=index.jsp");
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
