package com.xiangwan.flashsale.logic.salegoods.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiangwan.flashsale.logic.salegoods.domain.SaleGoods;

public interface SaleGoodsDao {

	@Select("select * from sale_goods where goodsId=#{goodsId}")
	public SaleGoods getSaleGoodsByGoodsId(@Param("goodsId")long goodsId);
	
	@Update("update sale_goods set stockCount=#{stockCount} where goodsId=#{goodsId}")
	public void reduceStock(SaleGoods saleGoods);
}
