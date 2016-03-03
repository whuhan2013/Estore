package com.zj.service;

import java.util.List;
import java.util.UUID;

import com.zj.dao.ProdDao;
import com.zj.domain.Page;
import com.zj.domain.Product;
import com.zj.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {

	ProdDao dao=BasicFactory.getFactory().getDao(ProdDao.class);
	@Override
	public void addProd(Product prod) {
		// TODO 自动生成的方法存根
		
		prod.setId(UUID.randomUUID().toString());
		dao.addProd(prod);
		
	}
	@Override
	public List<Product> findAllProd() {
		// TODO 自动生成的方法存根
		return dao.findAllProd();
	}
	@Override
	public Product findProdById(String id) {
		// TODO 自动生成的方法存根
		return dao.findProdById(id);
	}
	@Override
	public Page pageProduct(int thispage, int rowperpage) {
		// TODO 自动生成的方法存根
		Page page = new Page();
		//--当前页
		page.setThispage(thispage);
		//--每页记录数
		page.setRowperpage(rowperpage);
		//--共有多少行
		int countrow = dao.getCountRow();
		page.setCountrow(countrow);
		//--共有多少页
		int countpage = countrow/rowperpage+(countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//--首页
		page.setFirstpage(1);
		//--尾页
		page.setLastpage(countpage);
		//--上一页
		page.setPrepage(thispage==1?1:thispage-1);
		//--下一页
		page.setNextpage(thispage == countpage ? countpage : thispage+1);
		//--当前页数据
		List<Product> list = dao.getProductByPage((thispage-1)*rowperpage,rowperpage);
		page.setList(list);
		
		return page;
	}

}
