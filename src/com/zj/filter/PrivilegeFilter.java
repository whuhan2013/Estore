package com.zj.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.User;

public class PrivilegeFilter implements Filter {

	private List<String> admin_list=new ArrayList<String>();
	private List<String> user_list=new ArrayList<String>();
	@Override
	public void destroy() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO 自动生成的方法存根
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		String uri=req.getRequestURI();
		if(admin_list.contains(uri)||user_list.contains(uri))
		{
			//说明当前资源需要权限
			if(req.getSession(false)==null||req.getSession().getAttribute("user")==null)
			{
				resp.getWriter().write("当前资源需要权限，请先登陆");
				return;
			}
			User user= (User) req.getSession().getAttribute("user");
			if(admin_list.contains(uri)&&"admin".equals(user.getRole()))
			{
				arg2.doFilter(arg0, arg1);
			}else if(user_list.contains(uri)&&"user".equals(user.getRole()))
			{
				arg2.doFilter(arg0, arg1);
			}else
			{
				throw new RuntimeException("您不具有相应权限");
			}
		}else
		{
			//说明不需要权限
		     arg2.doFilter(arg0, arg1);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		ServletContext context=arg0.getServletContext();
		try {
			BufferedReader adminReader=new BufferedReader(new FileReader(context.getRealPath("WEB-INF/admin.txt")));
			String line=null;
			while((line=adminReader.readLine())!=null)
			{
				admin_list.add(line);
			}
			
			BufferedReader userReader=new BufferedReader(new FileReader(context.getRealPath("WEB-INF/user.txt")));
			 line=null;
			while((line=userReader.readLine())!=null)
			{
				user_list.add(line);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

}
