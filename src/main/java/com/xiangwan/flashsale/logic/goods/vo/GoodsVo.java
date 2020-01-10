package com.xiangwan.flashsale.logic.goods.vo;

import java.util.Date;

import com.xiangwan.flashsale.logic.goods.domain.Goods;

/**
 * 携带部分秒杀表信息
 * @author Administrator
 *
 */
public class GoodsVo extends Goods {
	private Double price;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
