package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.service.UserService;
import com.zj.util.MD5Utils;

public class RegisterServlet extends HttpServlet {

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
		
		UserService service=BasicFactory.getFactory().getService(UserService.class);

		//������֤��
		String valistr1=request.getParameter("valistr");
		String valistr2=(String) request.getSession().getAttribute("valistr");
		if(valistr1==null||valistr2==null||!valistr1.equals(valistr2))
		{
			request.setAttribute("msg", "<font color='red'>��֤�벻��ȷ!</font> ");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		try
		{
		//��װ���ݽ�������
		User user=new User();
		BeanUtils.populate(user, request.getParameterMap());
		user.setPassword(MD5Utils.md5(user.getPassword()));
		
		//����SERVICEע���û�
		service.regist(user);
		//�ض������ҳ
		response.getWriter().write("ע��ɹ����뵽�����н��м���...");
		response.setHeader("Refresh", "3;url=index.jsp");
		}catch(Exception e)
		{
			e.printStackTrace();
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
