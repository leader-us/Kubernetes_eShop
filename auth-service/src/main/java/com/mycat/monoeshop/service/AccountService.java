package com.mycat.monoeshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycat.monoeshop.mapper.AccountMapper;
import com.mycat.monoeshop.model.Account;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountMapper accountMapper;

    public Account login(String username, String password) {
        LOGGER.info("account login, username: {}, password: {}", username, password);
        return accountMapper.getAccountByNameAndPwd(username, password);
    }
}
