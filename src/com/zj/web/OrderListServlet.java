package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.Order;
import com.zj.domain.OrderListForm;
import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.service.OrderService;

public class OrderListServlet extends HttpServlet {

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
		//获取用户编号
		User user=(User) request.getSession().getAttribute("user");
		int id=user.getId();
		//调用service中根据用户ID查询订单的方法
		List<OrderListForm> list= service.findOrders(id);
		//存入request域中，带入
		request.setAttribute("list", list);
		request.getRequestDispatcher("orderList.jsp").forward(request, response);
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
