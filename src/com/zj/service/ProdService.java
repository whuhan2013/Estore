package com.zj.service;

import java.util.List;

import com.zj.domain.Page;
import com.zj.domain.Product;

public interface ProdService extends Service{

	/**
	 * �����Ʒ
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * ����ID����ָ����Ʒ
	 * @param parameter
	 * @return
	 */
	Product findProdById(String id);

	/**
	 * ��ҳ����
	 * @param thispage
	 * @param rowperpage
	 * @return
	 */
	Page pageProduct(int thispage, int rowperpage);

}
