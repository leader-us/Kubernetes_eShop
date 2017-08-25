package com.mycat.monoeshop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycat.monoeshop.mapper.ProductMapper;
import com.mycat.monoeshop.model.Product;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProducts() {
        LOGGER.info("request for all products");
        List<Product> products = productMapper.getProducts();
        LOGGER.info("product size: {}", products == null ? 0 : products.size());
        return products;
    }

    public Product getProductById(Integer id) {
       
        Product result=productMapper.getProductById(id);
        LOGGER.info("request for productId: {},founded {}", id,result!=null);
        return  result;
    }
}
