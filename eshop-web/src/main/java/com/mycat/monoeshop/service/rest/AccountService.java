package com.mycat.monoeshop.service.rest;

import com.mycat.monoeshop.model.Account;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public interface AccountService {

	@Headers({ "Content-Type: application/json" })
	@RequestLine("POST /account/login?username={username}&password={password}")
	Account login(@Param("username") String username, @Param("password") String password);
}
