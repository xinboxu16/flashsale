package com.xiangwan.flashsale.logic.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangwan.flashsale.logic.goods.service.GoodsService;
import com.xiangwan.flashsale.logic.goods.vo.GoodsVo;
import com.xiangwan.flashsale.logic.user.domain.User;
import com.xiangwan.flashsale.logic.user.service.UserService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 跳转到商品列表页面
	 * 下面注释的代码使用UserArgumentResolver来实现 将返回值直接放到参数user中
	 * @return
	 */
	@RequestMapping("/to_list")
	public String toList(Model model, 
			//HttpServletResponse response, 
			//@CookieValue(value=AppConstants.COOKIE_TOKEN, required=false) String cookieToken,
			//@RequestParam(value=AppConstants.COOKIE_TOKEN, required=false) String paramToken,
			User user) {
		
		/*if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return "login";
		}
		
		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
		
		User user = userService.getUserByToken(response, token);
		*/
		
		if (user == null) {
			//throw new BaseException(AppConstants.USER_ERROR);
			return "login";
		}
		
		model.addAttribute("user", user);
		
		//通过Jmeter压力测试这个方法发现只有这个地方会消耗性能 这是请求数据库 说明瓶颈在数据库这里
		//查询商品列表
		List<GoodsVo> goodsList = this.goodsService.getListGoodsVo();
		model.addAttribute("goodsList", goodsList);
		
		return "goods_list";
	}
	
	/**
	 * 跳转到商品详情页面
	 * 下面注释的代码使用UserArgumentResolver来实现 将返回值直接放到参数user中
	 * @return
	 */
	@RequestMapping("/to_detail/{goodsId}")
	public String toDetail(Model model, 
			//HttpServletResponse response, 
			//@CookieValue(value=AppConstants.COOKIE_TOKEN, required=false) String cookieToken,
			//@RequestParam(value=AppConstants.COOKIE_TOKEN, required=false) String paramToken,
			User user, @PathVariable("goodsId")long goodsId) {
		//数据库一般不使用主键自增 很容易从0开始遍历出来 使用snowflake

		if (user == null) {
			//throw new BaseException(AppConstants.USER_ERROR);
			return "login";
		}
		
		model.addAttribute("user", user);
		
		//查询商品
		GoodsVo goodsVo = this.goodsService.getGoodsVoByGoodsId(goodsId);
		model.addAttribute("goods", goodsVo);
		
		//秒杀状态  ==1秒杀进行中  ==0秒杀还没有开始 倒计时 ==2 秒杀已经结束
		int saleStatus = 1;
		//还没开始的剩余时间
		int saleRemainSecond = 0;
		
		//秒杀开始结束时间
		long startAt = goodsVo.getStartDate().getTime();
		long endAt = goodsVo.getEndDate().getTime();
		long now = System.currentTimeMillis();
		
		if (now < startAt) {//秒杀还没有开始 倒计时
			saleStatus = 0;
			saleRemainSecond = (int)((startAt-now)/1000);
		} else if (now > endAt) {//秒杀已经结束
			saleStatus = 2;
			saleRemainSecond = -1;
		}
		model.addAttribute("saleStatus", saleStatus);
		model.addAttribute("saleRemainSecond", saleRemainSecond);
		return "goods_detail";
	}
}
