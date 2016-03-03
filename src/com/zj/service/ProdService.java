package com.zj.service;

import java.util.List;

import com.zj.domain.Page;
import com.zj.domain.Product;

public interface ProdService extends Service{

	/**
	 * 添加商品
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * 查询所有商品
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * 根据ID查找指定商品
	 * @param parameter
	 * @return
	 */
	Product findProdById(String id);

	/**
	 * 分页方法
	 * @param thispage
	 * @param rowperpage
	 * @return
	 */
	Page pageProduct(int thispage, int rowperpage);

}
