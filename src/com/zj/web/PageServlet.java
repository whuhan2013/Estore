package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.Page;
import com.zj.factory.BasicFactory;
import com.zj.service.ProdService;

public class PageServlet extends HttpServlet {

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
		//��ȡ��ǰҳ��
		//��ȡ��ǰ��ҳ���ÿҳ��¼��
		        
				 int thispage=Integer.parseInt(request.getParameter("thispage"));
				 //int thispage=request.getParameter("thispage");
				int rowperpage=6;
				
				//����Service�з��������ҿͻ���Ϣ
				Page page = service.pageProduct(thispage,rowperpage);
				//3.����request����,����pageList.jspҳ���н�����ʾ
				request.setAttribute("page", page);
				request.getRequestDispatcher("Estore.jsp").forward(request, response);
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
