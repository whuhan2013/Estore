package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.Product;
import com.zj.factory.BasicFactory;
import com.zj.service.ProdService;

public class ProdInfoServlet extends HttpServlet {

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

		ProdService service = BasicFactory.getFactory().getService(ProdService.class);
		//1.获取要查询的商品id
		String id = request.getParameter("id");
		//2.调用Service中的方法查询制定id的商品
		Product prod = service.findProdById(id);
		//3.将查到的商品存入request域后带到页面显示
		if(prod==null){
			throw new RuntimeException("找不到该商品!!!");
		}else{
			request.setAttribute("prod", prod);
			request.getRequestDispatcher("prodInfo.jsp").forward(request, response);
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
