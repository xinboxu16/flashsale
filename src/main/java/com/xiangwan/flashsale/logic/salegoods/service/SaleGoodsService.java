package com.xiangwan.flashsale.logic.salegoods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangwan.flashsale.logic.goods.domain.Goods;
import com.xiangwan.flashsale.logic.goods.service.GoodsService;
import com.xiangwan.flashsale.logic.goods.vo.GoodsVo;
import com.xiangwan.flashsale.logic.order.domain.OrderInfo;
import com.xiangwan.flashsale.logic.order.service.OrderInfoService;
import com.xiangwan.flashsale.logic.salegoods.dao.SaleGoodsDao;
import com.xiangwan.flashsale.logic.salegoods.domain.SaleGoods;
import com.xiangwan.flashsale.logic.saleorder.service.SaleOrderService;
import com.xiangwan.flashsale.logic.user.domain.User;

@Service
public class SaleGoodsService {
	
	@Autowired
	private SaleGoodsDao saleGoodsDao;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private SaleOrderService saleGoodsService;
	
	/**
	 * 减库存 下订单 写入秒杀订单 返回生成的订单
	 * 这些步骤需要在一个事务中同时完成或失败
	 * @param user
	 * @param goodsVo
	 * @return
	 */
	@Transactional
	public OrderInfo doSale(User user, GoodsVo goodsVo) {
		//减库存
		boolean isEnough = this.goodsService.reduceStock(goodsVo, 1);
		isEnough = isEnough && this.reduceStock(goodsVo, 1);
		
		if (!isEnough) {
			return null;
		}
		
		//写订单 创建order
		OrderInfo orderInfo = this.orderInfoService.createOrder(user,goodsVo);
		//订单写到秒杀order
		this.saleGoodsService.createSaleOrder(orderInfo);
		
		return orderInfo;
	}

	/**
	 * 减少SaleOrder库存的方法
	 * @param goodsVo
	 */
	public boolean reduceStock(GoodsVo goodsVo, int reduceNum) {
		//减库存
		SaleGoods saleGoods = this.saleGoodsDao.getSaleGoodsByGoodsId(goodsVo.getId());
		int stockCount = saleGoods.getStockCount() - reduceNum;
		if (stockCount < 0) {
			return false;
		}
		saleGoods.reduceStockCount(1);
		//减少库存
		this.saleGoodsDao.reduceStock(saleGoods);
		return true;
	}

}
