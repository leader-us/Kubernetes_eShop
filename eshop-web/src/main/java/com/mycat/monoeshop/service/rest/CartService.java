package com.mycat.monoeshop.service.rest;

import java.util.List;

import com.mycat.monoeshop.model.CartRecord;
import com.mycat.monoeshop.model.Result;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public interface CartService {

	@Headers({ "Content-Type: application/json" })
	@RequestLine("GET /cart/records/{user}")
	public List<CartRecord> getProductsByUsername(@Param("user") String username);

	@Headers({ "Content-Type: application/json" })
	@RequestLine("GET /cart/add-cart")
	public Result<String> addProductToCart(CartRecord cartRecord);
}
