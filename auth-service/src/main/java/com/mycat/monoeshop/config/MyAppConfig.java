package com.mycat.monoeshop.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
@Configuration
public class MyAppConfig extends CachingConfigurerSupport {

	@Configuration
	public class DruidDataSourceConfig {
		@Bean
		@ConfigurationProperties(prefix = "spring.datasource")
		public DataSource druidDataSource() {
			DruidDataSource druidDataSource = new DruidDataSource();
			return druidDataSource;
		}
	}

}
