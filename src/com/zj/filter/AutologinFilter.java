package com.zj.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.domain.User;
import com.zj.factory.BasicFactory;
import com.zj.service.UserService;

public class AutologinFilter implements Filter {

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO �Զ����ɵķ������
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		//1.ֻ��δ��¼���û����Զ���¼
		if(req.getSession(false)==null || req.getSession().getAttribute("user")==null){
			//2.ֻ�д����Զ���½cookie���û����Զ���½
			Cookie [] cs = req.getCookies();
			Cookie findC = null;
			if(cs!=null){
				for(Cookie c : cs){
					if("autologin".equals(c.getName())){
						findC = c;
						break;
					}
				}
			}
			if(findC!=null){
				//3.ֻ���Զ���¼cookie�е��û������붼��ȷ�����Զ���½
				String v = URLDecoder.decode(findC.getValue(),"utf-8");
				String username = v.split(":")[0];
				String password = v.split(":")[1];
				UserService service = BasicFactory.getFactory().getService(UserService.class);
				User user = service.getUserByNameAndPSW(username, password);
				if(user!=null){
					req.getSession().setAttribute("user", user);
				}
			}
		}
		
		
		//4.�����Ƿ��Զ���½��Ҫ����
		arg2.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO �Զ����ɵķ������

	}

}
