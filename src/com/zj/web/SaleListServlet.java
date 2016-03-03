package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.SaleInfo;
import com.zj.factory.BasicFactory;
import com.zj.service.OrderService;

public class SaleListServlet extends HttpServlet {

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


		//调用service中查询销售榜单的方法
		OrderService service=BasicFactory.getFactory().getService(OrderService.class);
		List<SaleInfo> list= service.saleList();
		System.out.println(list);
		//将销售榜单信息组织成CSV格式数据
		StringBuffer buffer=new StringBuffer();
		buffer.append("商品编号，商品名称，销售数量\r\n");
		for(SaleInfo si:list)
		{
			buffer.append(si.getProd_id()+","+si.getProd_name()+","+si.getSale_num()+"\r\n");
		}
		String data=buffer.toString();
		System.out.println(data);
		//提供下载
		String filename="销售榜单_"+new Date().toLocaleString()+".csv";
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
		response.setContentType(this.getServletContext().getMimeType(filename));
		response.getWriter().write(data);
		//response.sendRedirect("/index.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
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
