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
		// TODO �Զ����ɵķ������
		
		prod.setId(UUID.randomUUID().toString());
		dao.addProd(prod);
		
	}
	@Override
	public List<Product> findAllProd() {
		// TODO �Զ����ɵķ������
		return dao.findAllProd();
	}
	@Override
	public Product findProdById(String id) {
		// TODO �Զ����ɵķ������
		return dao.findProdById(id);
	}
	@Override
	public Page pageProduct(int thispage, int rowperpage) {
		// TODO �Զ����ɵķ������
		Page page = new Page();
		//--��ǰҳ
		page.setThispage(thispage);
		//--ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//--���ж�����
		int countrow = dao.getCountRow();
		page.setCountrow(countrow);
		//--���ж���ҳ
		int countpage = countrow/rowperpage+(countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//--��ҳ
		page.setFirstpage(1);
		//--βҳ
		page.setLastpage(countpage);
		//--��һҳ
		page.setPrepage(thispage==1?1:thispage-1);
		//--��һҳ
		page.setNextpage(thispage == countpage ? countpage : thispage+1);
		//--��ǰҳ����
		List<Product> list = dao.getProductByPage((thispage-1)*rowperpage,rowperpage);
		page.setList(list);
		
		return page;
	}

}
