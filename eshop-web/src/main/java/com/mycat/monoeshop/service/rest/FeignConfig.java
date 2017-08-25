package com.mycat.monoeshop.service.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mycat.monoeshop.model.Account;
import com.mycat.monoeshop.model.CartRecord;
import com.mycat.monoeshop.model.Product;
import com.mycat.monoeshop.model.Result;
import com.mycat.monoeshop.model.ResultEnum;

import feign.Request;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Configuration
public class FeignConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	@Value("${myapp.restserviceurl.account}")
	private String accountrestServiceUrl;
	@Value("${myapp.restserviceurl.cart}")
	private String cartrestServiceUrl;
	@Value("${myapp.restserviceurl.product}")
	private String productrestServiceUrl;

	@Bean
	public AccountService accountService() {
		return buildFeignClient(accountrestServiceUrl, AccountService.class, new AccountService() {

			@Override
			public Account login(String username, String password) {
				LOGGER.warn("Hystrix occured: login ");
				return null;
			}
		});
	}

	@Bean
	public CartService cartService() {
		return buildFeignClient(cartrestServiceUrl, CartService.class, new CartService() {

			@Override
			public List<CartRecord> getProductsByUsername(String username) {
				LOGGER.warn("Hystrix occured :getProductsByUsername");
				CartRecord prod=new CartRecord();
				prod.setCount(0);
				prod.setProductId(0);
				prod.setProductName("Error");
				prod.setProductPrice(BigDecimal.ZERO);
				ArrayList<CartRecord> rest=new ArrayList<>();
				rest.add(prod);
				return rest;
			}

			@Override
			public Result<String> addProductToCart(CartRecord cartRecord) {
				LOGGER.warn("Hystrix occured :addProductToCart");
				return new Result<String>(ResultEnum.ERROR);
			}

		});
	}

	@Bean
	public ProductService productService() {
		return buildFeignClient(productrestServiceUrl, ProductService.class, new ProductService() {

			@Override
			public List<Product> getProducts() {
				LOGGER.warn("Hystrix occured: getProducts");
				Product prod=new Product();
				prod.setId(0);
				prod.setName("Error");
				prod.setPrice(BigDecimal.ZERO);
				prod.setDesc("Erro occured");
				ArrayList<Product> rest=new ArrayList<>();
				rest.add(prod);
				return rest;
			}

			@Override
			public Product getProductById(Integer id) {
				LOGGER.warn("Hystrix occured: getProductById");
				return null;
			}

		});
	}

	private <T> T buildFeignClient(String restPath, Class<T> service, T fallback) {
		Request.Options options = new Request.Options(5000, 10000);
		T result = HystrixFeign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).options(options)
				.target(service, restPath, fallback);
		return result;

	}

}
