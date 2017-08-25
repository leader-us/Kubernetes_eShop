package com.mycat.monoeshop.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycat.monoeshop.model.CartRecord;
import com.mycat.monoeshop.model.Result;
import com.mycat.monoeshop.service.CartService;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@RestController()
@RequestMapping("/cart")
public class CartRestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartRestService.class);
	@Autowired
	private CartService cartService;

	@RequestMapping("records/{user}")
	public List<CartRecord> getProductsByUsername( @PathVariable("user") String name) {
		LOGGER.info("get cart for user " + name);
		return cartService.getProductsByUsername(name);
	}

	@RequestMapping("add-cart")
	public Result<String> addProductToCart(@RequestBody CartRecord cartRecord) {
		LOGGER.info("add goods to cart  " + cartRecord);
		return cartService.addProductToCart(cartRecord);
	}
}
