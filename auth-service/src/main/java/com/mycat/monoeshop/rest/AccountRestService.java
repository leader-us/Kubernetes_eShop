package com.mycat.monoeshop.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycat.monoeshop.model.Account;
import com.mycat.monoeshop.service.AccountService;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@RestController
@RequestMapping("/account")
public class AccountRestService {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Account login(HttpServletRequest request, String username, String password) {
		Account accont = accountService.login(username, password);
		return accont;
	}
}
