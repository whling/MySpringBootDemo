package com.whl.multidatasource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.alibaba.druid.pool.DruidDataSource;
import com.whl.customer.MultiDataSourceRegisterAdapter;

/**
 * 
 * description: 使用java配置的方式注册数据源
 * 
 * @author whling
 * @date 2017年6月10日 下午11:37:43
 *
 */
@Configuration
@ConditionalOnClass(MultiDataSourceRegisterAdapter.class)
@DependsOn(value={"master","slave01","slave02"})
public class MultiDataSourceRegister {
	private static final String MASTER = "master";
	private static final String SLAVE01 = "slave01";
	private static final String SLAVE02 = "slave02";
	@Resource(name = "master")
	private DruidDataSource masterDataSource;
	@Resource(name = "slave01")
	private DruidDataSource slave01DataSource;
	@Resource(name = "slave02")
	private DruidDataSource slave02DataSource;
	/**
	 * 初始化DynamicDataSource，并往targetDataSources属性注入一个map,
	 * 这个map是用户自定义数据源KV对，如需添加数据源的时候要更改这里的map初始数据
	 * @return
	 */
	@Bean(name = "dataSource")
	public DataSource getDynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(MASTER, masterDataSource);
		targetDataSources.put(SLAVE01, slave01DataSource);
		targetDataSources.put(SLAVE02, slave02DataSource);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		return dynamicDataSource;
	}
}
