package com.zj.dao;

import java.util.List;

import com.zj.domain.Product;

public interface ProdDao extends Dao{

	/**
	 * 添加商品方法
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * 查询所有商品的方法
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * 根据ID查找商品
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

	/**
	 * 扣除商品库存数量的方法
	 * @param product_id
	 * @param buynum
	 */
	void delpnum(String product_id, int buynum);

	/**
	 * 添加商品库存数量的方法
	 * @param product_id
	 * @param buynum
	 */
	void addpnum(String product_id, int buynum);

	/**
	 * 共有多少页
	 * @return
	 */
	int getCountRow();

	/**
	 * 分页得到货物信息
	 * @param i
	 * @param rowperpage
	 * @return
	 */
	List<Product> getProductByPage(int i, int rowperpage);

}
