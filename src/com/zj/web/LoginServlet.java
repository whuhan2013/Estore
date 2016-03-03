package com.zj.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.service.UserService;
import com.zj.util.MD5Utils;

public class LoginServlet extends HttpServlet {

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

		//获取用户名与密码
		String username=request.getParameter("username");
		String password=MD5Utils.md5(request.getParameter("password"));
		
		//较验是否正确
		User user=service.getUserByNameAndPSW(username,password);
		if(user==null)
		{
			request.setAttribute("msg", "用户名密码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		//检查用户激活状态
		if(user.getState()==0)
		{
			request.setAttribute("msg", "用户尚未激活，请到邮箱中激活");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//重定向回主页
		request.getSession().setAttribute("user", user);
		
		
		//记住用户名
		if("true".equals(request.getParameter("remname"))){
			Cookie remnameC = new Cookie("remname",URLEncoder.encode(user.getUsername(),"utf-8"));
			remnameC.setPath("/");
			remnameC.setMaxAge(3600*24*30);
			response.addCookie(remnameC);
			
		}else{
			Cookie remnameC = new Cookie("remname","");
			remnameC.setPath("/");
			remnameC.setMaxAge(0);
			response.addCookie(remnameC);
		}
		

		//--处理30天内自动登陆
		if("true".equals(request.getParameter("autologin"))){
			Cookie autologinC = new Cookie("autologin",URLEncoder.encode(user.getUsername()+":"+user.getPassword(),"utf-8"));
			autologinC.setPath("/");
			autologinC.setMaxAge(3600*24*30);
			response.addCookie(autologinC);
		}
		
		response.sendRedirect("index.jsp");
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
