package com.xiangwan.flashsale.logic.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.xiangwan.flashsale.logic.order.domain.OrderInfo;

public interface OrderDao {

	/**
	 * keyProperty：对应的domain 对象中需要被赋值的属性，一般是主键
	 * SelectKey需要注意order属性，像MySQL一类支持自动增长类型的数据库中，order需要设置为after才会取到正确的值，像Oracle这样取序列的情况，需要设置为before
	 * @param orderInfo
	 * @return
	 */
	@Insert("insert into order_info(userId, goodsId,goodsCount,goodsPrice,orderChannel,status,createDate)"
			+ "values(#{userId}, #{goodsId},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
	@SelectKey(keyColumn="id",keyProperty="id",resultType=long.class,before=false,statement="select last_insert_id()")
	public long insert(OrderInfo orderInfo);

}
