package com.mycat.monoeshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Controller
public class Mainpage {
	@RequestMapping("/")
    public String index()
    {
    	return "/main.html";
    }
}
