package com.mycat.monoeshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycat.monoeshop.model.Product;
import com.mycat.monoeshop.service.rest.ProductService;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping("")
	@ResponseBody
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping("{id}")
	@ResponseBody
	public Product getProductById(@PathVariable Integer id) {
		return productService.getProductById(id);
	}
}
