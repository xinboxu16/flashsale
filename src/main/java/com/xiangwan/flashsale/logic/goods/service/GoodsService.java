package com.xiangwan.flashsale.logic.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiangwan.flashsale.logic.goods.dao.GoodsDao;
import com.xiangwan.flashsale.logic.goods.domain.Goods;
import com.xiangwan.flashsale.logic.goods.vo.GoodsVo;
import com.xiangwan.flashsale.logic.salegoods.domain.SaleGoods;

@Service
public class GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	public List<GoodsVo> getListGoodsVo() {
		return this.goodsDao.getListGoodsVo();
	}

	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return this.goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	/**
	 * 减少Goods库存的方法
	 * @param goodsVo
	 */
	public boolean reduceStock(GoodsVo goodsVo, int reduceNum) {
		Goods goods = this.goodsDao.getGoodsVoByGoodsId(goodsVo.getId());
		int goodsStock = goods.getGoodsStock();
		if (goodsStock == -1) {
			return true;
		}
		goodsStock = goodsStock - reduceNum;
		if (goodsStock >= 0) {
			goods.reduceGoodsStock(reduceNum);
			//减少库存
			this.goodsDao.reduceStock(goods);
		} else {
			return false;
		}
		return true;
	}
}
