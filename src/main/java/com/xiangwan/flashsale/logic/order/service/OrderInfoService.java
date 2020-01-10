package com.xiangwan.flashsale.logic.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiangwan.flashsale.logic.goods.vo.GoodsVo;
import com.xiangwan.flashsale.logic.order.dao.OrderDao;
import com.xiangwan.flashsale.logic.order.domain.OrderInfo;
import com.xiangwan.flashsale.logic.saleorder.dao.SaleOrderDao;
import com.xiangwan.flashsale.logic.user.domain.User;

@Service
public class OrderInfoService {
	
	@Autowired
	public OrderDao orderDao;

	public OrderInfo createOrder(User user, GoodsVo goodsVo) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goodsVo.getId());
		orderInfo.setGoodsPrice(goodsVo.getPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);//新建未支付
		orderInfo.setUserId(user.getId());
		this.orderDao.insert(orderInfo);
		//上面的语句会自动返回主键到orderinfo中
		//orderInfo.setId(orderId);
		return orderInfo;
	}

}
