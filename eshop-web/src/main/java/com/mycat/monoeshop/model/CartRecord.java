package com.mycat.monoeshop.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public class CartRecord {
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private String username;
    private Date time;
    private Integer count;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartRecord{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", username='" + username + '\'' +
                ", time=" + time +
                ", count=" + count +
                '}';
    }
}
