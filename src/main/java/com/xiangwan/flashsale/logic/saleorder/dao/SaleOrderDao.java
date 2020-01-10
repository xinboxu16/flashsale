package com.xiangwan.flashsale.logic.saleorder.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiangwan.flashsale.logic.saleorder.domain.SaleOrder;

public interface SaleOrderDao {
	
	@Select("select * from sale_order where userId=#{userId} and goodsId=#{goodsId}")
	public SaleOrder getSaleOrderByUidGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

	@Insert("insert into sale_order(userId, goodsId,orderId)"
			+ "values(#{userId}, #{goodsId},#{orderId})")
	public int insert(SaleOrder saleOrder);

}
