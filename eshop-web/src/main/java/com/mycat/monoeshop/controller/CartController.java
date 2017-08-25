package com.mycat.monoeshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycat.monoeshop.App;
import com.mycat.monoeshop.model.CartRecord;
import com.mycat.monoeshop.model.Result;
import com.mycat.monoeshop.service.rest.CartService;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@RequestMapping("records")
	@ResponseBody
	public List<CartRecord> getProductsByUsername(HttpServletRequest request) {
		String name = (String) request.getSession().getAttribute(App.SESSION_KEY);
		LOGGER.info("get cart for user " + name);
		return cartService.getProductsByUsername(name);
	}

	@RequestMapping("add-cart")
	@ResponseBody
	public Result<String> addProductToCart(@RequestBody CartRecord cartRecord, HttpServletRequest request) {
		String name = (String) request.getSession().getAttribute(App.SESSION_KEY);
		cartRecord.setUsername(name);
		LOGGER.info("add goods to cart  " + cartRecord);
		return cartService.addProductToCart(cartRecord);
	}
}
