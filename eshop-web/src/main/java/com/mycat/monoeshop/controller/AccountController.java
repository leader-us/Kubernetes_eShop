package com.mycat.monoeshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycat.monoeshop.App;
import com.mycat.monoeshop.model.Account;
import com.mycat.monoeshop.service.rest.AccountService;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(HttpServletRequest request,String username, String password) {
    	Account accont= accountService.login(username, password);
    	if(accont!=null)
    	{
    		request.getSession().setAttribute(App.SESSION_KEY	, accont.getName());
    		return "redirect:/main.html";
    	}else
    	{
    		return "redirect:/login.html";
    	}
    }
    @RequestMapping(value="/")
    public String index()
    {
    	return "/main.html";
    }
}
