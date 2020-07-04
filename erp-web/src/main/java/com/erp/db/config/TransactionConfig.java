/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.erp.db.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 事务回滚
 * 
 * @author Lenovo
 *
 */
@Configuration
public class TransactionConfig {

	@Autowired
	DataSource dataSource;

	@Bean("transactionManager")
	public DataSourceTransactionManager jpaTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
