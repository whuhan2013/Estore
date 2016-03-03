package com.zj.dao;

import java.util.List;

import com.zj.domain.Product;

public interface ProdDao extends Dao{

	/**
	 * �����Ʒ����
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * ��ѯ������Ʒ�ķ���
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * ����ID������Ʒ
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

	/**
	 * �۳���Ʒ��������ķ���
	 * @param product_id
	 * @param buynum
	 */
	void delpnum(String product_id, int buynum);

	/**
	 * �����Ʒ��������ķ���
	 * @param product_id
	 * @param buynum
	 */
	void addpnum(String product_id, int buynum);

	/**
	 * ���ж���ҳ
	 * @return
	 */
	int getCountRow();

	/**
	 * ��ҳ�õ�������Ϣ
	 * @param i
	 * @param rowperpage
	 * @return
	 */
	List<Product> getProductByPage(int i, int rowperpage);

}
