package com.zj.listener;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zj.domain.Product;

public class MyHSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO �Զ����ɵķ������
		arg0.getSession().setAttribute("cartmap", new LinkedHashMap<Product, Integer>());
        
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO �Զ����ɵķ������

	}

}
