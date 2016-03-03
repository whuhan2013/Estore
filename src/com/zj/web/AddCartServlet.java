package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.Product;
import com.zj.factory.BasicFactory;
import com.zj.service.ProdService;

public class AddCartServlet extends HttpServlet {

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
  
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
		
		//根据ID查找出要购买的商品
		String id=request.getParameter("id");
		Product prod=service.findProdById(id);
		//向CartMap添加商品,如果之前没买过，则添加并设置数量为1，如果已经有过，则数量加1
		if(prod==null)
		{
			throw new RuntimeException("找不到该商品");
		}else
		{
			Map<Product, Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			cartmap.put(prod, cartmap.containsKey(prod)?cartmap.get(prod)+1:1);
			
		}
		
		//重定向到购物车页面
		response.sendRedirect("cart.jsp");
		
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

	}

}
