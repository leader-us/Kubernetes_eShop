package com.mycat.monoeshop.service.rest;

import java.util.List;

import com.mycat.monoeshop.model.Product;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */

public interface ProductService {
	@Headers({ "Content-Type: application/json" })
	@RequestLine("GET /products/all")
	public List<Product> getProducts();

	@Headers({ "Content-Type: application/json" })
	@RequestLine("GET /products/{id}")
	public Product getProductById(@Param("id") Integer id);
}
