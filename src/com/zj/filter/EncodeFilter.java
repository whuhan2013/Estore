package com.zj.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {
	private FilterConfig config=null;
	private ServletContext context=null;
	private String encode=null;

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO 自动生成的方法存根
		//响应乱码解决
		arg1.setCharacterEncoding(encode);
		arg1.setContentType("text/html;charset="+encode);
		//请求乱码解决
		
		arg2.doFilter(new MyHttpServletRequest((HttpServletRequest) arg0), arg1);
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		this.config=arg0;
		this.context=arg0.getServletContext();
		this.encode=context.getInitParameter("encode");

	}
	
	private class MyHttpServletRequest extends HttpServletRequestWrapper
	{
		private HttpServletRequest request=null;
        private boolean isNotEncode=true;
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			// TODO 自动生成的构造函数存根
			this.request=request;
		}
		
		@Override
		public String getParameter(String name) {
			// TODO 自动生成的方法存根
			return getParameterValues(name)==null?null:getParameterValues(name)[0];
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			// TODO 自动生成的方法存根
			try
			{
			if(request.getMethod().equalsIgnoreCase("post"))
			{
				request.setCharacterEncoding(encode);
				return request.getParameterMap();
			}else if(request.getMethod().equalsIgnoreCase("GET"))
			{
				Map<String,String[]> map=request.getParameterMap();
				if(isNotEncode)
				{
				for(Map.Entry<String, String[]> entry:map.entrySet())
				{
					String []vs=entry.getValue();
					for(int i=0;i<vs.length;i++)
					{
						vs[i]=new String(vs[i].getBytes("iso8859-1"),encode);
					}
				}
				isNotEncode=false;
				}
				return map;
			}else
			{
			    return request.getParameterMap();
			}
			//return super.getParameterMap();
			}catch(Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		@Override
		public String[] getParameterValues(String name) {
			// TODO 自动生成的方法存根
			return getParameterMap().get(name);
		}
	}

}
