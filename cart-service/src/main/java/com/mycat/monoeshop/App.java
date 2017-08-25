package com.mycat.monoeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@SpringBootApplication
public class App {
	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "myapp_cur_user";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
