package com.xiangwan.flashsale.logic.saleorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiangwan.flashsale.logic.order.domain.OrderInfo;
import com.xiangwan.flashsale.logic.saleorder.dao.SaleOrderDao;
import com.xiangwan.flashsale.logic.saleorder.domain.SaleOrder;

@Service
public class SaleOrderService {
	
	@Autowired
	private SaleOrderDao saleOrderDao;

	public SaleOrder getSaleOrderByUidGoodsId(long userId, long goodsId) {
		return this.saleOrderDao.getSaleOrderByUidGoodsId(userId, goodsId);
	}

	/**
	 * 创建秒杀订单
	 * @param orderInfo
	 */
	public void createSaleOrder(OrderInfo orderInfo) {
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setGoodsId(orderInfo.getGoodsId());
		saleOrder.setOrderId(orderInfo.getId());
		saleOrder.setUserId(orderInfo.getUserId());
		this.saleOrderDao.insert(saleOrder);
	}

}
