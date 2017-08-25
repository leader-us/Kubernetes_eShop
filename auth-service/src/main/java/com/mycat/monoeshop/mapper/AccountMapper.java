package com.mycat.monoeshop.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mycat.monoeshop.model.Account;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public interface AccountMapper {
    @Select("select * from account where name=#{name,jdbcType=VARCHAR} and password=#{password,jdbcType=VARCHAR}")
    Account getAccountByNameAndPwd(@Param("name") String name, @Param("password") String password);
}
