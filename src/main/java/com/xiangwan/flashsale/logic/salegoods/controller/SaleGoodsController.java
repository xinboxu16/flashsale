package com.xiangwan.flashsale.logic.salegoods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangwan.flashsale.constants.AppConstants;
import com.xiangwan.flashsale.logic.goods.service.GoodsService;
import com.xiangwan.flashsale.logic.goods.vo.GoodsVo;
import com.xiangwan.flashsale.logic.order.domain.OrderInfo;
import com.xiangwan.flashsale.logic.salegoods.service.SaleGoodsService;
import com.xiangwan.flashsale.logic.saleorder.domain.SaleOrder;
import com.xiangwan.flashsale.logic.saleorder.service.SaleOrderService;
import com.xiangwan.flashsale.logic.user.domain.User;

@Controller
@RequestMapping("/salegoods")
public class SaleGoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	@Autowired
	private SaleGoodsService saleGoodsService;
	
	//秒杀页面
	@RequestMapping("/do_salegoods")
	public String listSaleGoods(Model model, User user, @RequestParam("goodsId") long goodsId) {
		if (user == null) {
			//throw new BaseException(AppConstants.USER_ERROR);
			return "login";
		}
		model.addAttribute("user", user);
		
		//判断库存
		GoodsVo goodsVo = this.goodsService.getGoodsVoByGoodsId(goodsId);
		int stockCount = goodsVo.getStockCount();
		if (stockCount <= 0) {
			model.addAttribute("errMsg", AppConstants.SALE_EMPTY_ERROR.getMsg());
			return "sale_fail";
		}
		
		//判断是否秒杀到
		SaleOrder saleOrder = this.saleOrderService.getSaleOrderByUidGoodsId(user.getId(), goodsId);
		if (saleOrder != null) {
			model.addAttribute("errMsg", AppConstants.SALE_REPEATE.getMsg());
			return "sale_fail";
		}
		
		//减库存 下订单 写入秒杀订单 返回生成的订单
		OrderInfo orderInfo = this.saleGoodsService.doSale(user, goodsVo);
		if (orderInfo == null) {
			model.addAttribute("errMsg", AppConstants.SALE_GOODS_LECK.getMsg());
			return "sale_fail";
		}
		
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goodsVo);
		return "order_detail";
	}
}
