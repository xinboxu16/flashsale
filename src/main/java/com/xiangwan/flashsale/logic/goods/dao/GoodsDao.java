package com.xiangwan.flashsale.logic.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiangwan.flashsale.logic.goods.domain.Goods;
import com.xiangwan.flashsale.logic.goods.vo.GoodsVo;

public interface GoodsDao {
	
	@Select("select g.*,s.price,s.stockCount,s.startDate,s.endDate from sale_goods s inner join goods g on s.goodsId = g.id")
	public List<GoodsVo> getListGoodsVo();

	@Select("select g.*,s.price,s.stockCount,s.startDate,s.endDate from sale_goods s inner join goods g on s.goodsId = g.id where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	@Update("update goods set goodsStock=#{goodsStock} where id=#{id}")
	public int reduceStock(Goods goods);
}
