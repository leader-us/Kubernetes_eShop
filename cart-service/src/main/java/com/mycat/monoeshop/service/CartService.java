package com.mycat.monoeshop.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mycat.monoeshop.model.CartRecord;
import com.mycat.monoeshop.model.Result;
import com.mycat.monoeshop.model.ResultEnum;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Service
public class CartService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	private static final String REDIS_KEY_PRE_CART = "cart_";

	@Autowired
	private RedisTemplate redisTemplate;

	public List<CartRecord> getProductsByUsername(String username) {
		try {
			List<CartRecord> records = redisTemplate.opsForList().range(REDIS_KEY_PRE_CART + username, 0, -1);
			LOGGER.info("get products by username: {}, result: {}", username, records);
			return records;
		} catch (Exception e) {
			LOGGER.warn("redis err ", e);
			return Collections.emptyList();
		}
	}

	public Result<String> addProductToCart(CartRecord cartRecord) {
		try {
			LOGGER.info("add product to cart, cartRecord: {}", cartRecord);
			String key = REDIS_KEY_PRE_CART + cartRecord.getUsername();
			ListOperations<String, CartRecord> optList = redisTemplate.opsForList();
			List<CartRecord> records = optList.range(key, 0, -1);
			boolean found = false;
			for (int i = 0; i < records.size(); i++) {
				CartRecord cur = records.get(i);
				if (cur.getProductId().equals(cartRecord.getProductId())) {
					cur.setCount(cur.getCount() + 1);
					optList.set(key, i, cur);
					found = true;
					break;
				}
			}
			if (!found) {
				redisTemplate.opsForList().leftPush(REDIS_KEY_PRE_CART + cartRecord.getUsername(), cartRecord)
						.intValue();
			}
			return new Result(ResultEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.warn("redis err ", e);
			Result<String> rs = new Result<>(ResultEnum.ERROR);
			rs.setData(e.toString());
			return rs;
		}
	}
}
