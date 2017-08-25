package com.mycat.monoeshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mycat.monoeshop.model.Product;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public interface ProductMapper {
    @Select("select * from product")
    List<Product> getProducts();

    @Select("select * from product where id=#{id,jdbcType=INTEGER}")
    Product getProductById(Integer id);
}
