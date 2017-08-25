package com.mycat.monoeshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycat.monoeshop.model.Product;
import com.mycat.monoeshop.service.ProductService;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@RestController
@RequestMapping("/products")
public class ProductRestService {
	@Autowired
	private ProductService productService;

	@RequestMapping("/all")
	@ResponseBody
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public Product getProductById(@PathVariable Integer id) {
		return productService.getProductById(id);
	}
}
