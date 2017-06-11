package com.whl.customer;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
/**
 * 
 * description: 使用java配置的方式注入3个数据源，一主两从
 * @author whling
 * @date 2017年6月10日 下午11:23:36
 *
 */
@Configuration
@EnableConfigurationProperties({ DruidMasterProperties.class, DruidSlave01Properties.class,
		DruidSlave02Properties.class })
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class MultiDataSourceRegisterAdapter {

	@Autowired
	private DruidMasterProperties masterProperties;
	@Autowired
	private DruidSlave01Properties slave01Properties;
	@Autowired
	private DruidSlave02Properties slave02Properties;

	@Bean(name="master")
	public DataSource getMasterDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(masterProperties.getDriver());
		dataSource.setUrl(masterProperties.getUrl());
		dataSource.setUsername(masterProperties.getUsername());
		dataSource.setPassword(masterProperties.getPassword());
		if (masterProperties.getInitialSize() > 0) {
			dataSource.setInitialSize(masterProperties.getInitialSize());
		}
		if (masterProperties.getMinIdle() > 0) {
			dataSource.setMinIdle(masterProperties.getMinIdle());
		}
		if (masterProperties.getMaxActive() > 0) {
			dataSource.setMaxActive(masterProperties.getMaxActive());
		}
		dataSource.setTestOnBorrow(masterProperties.isTestOnBorrow());
		try {
			dataSource.init();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dataSource;
	}

	@Bean(name="slave01")
	public DataSource getSlave01DataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(slave01Properties.getDriver());
		dataSource.setUrl(slave01Properties.getUrl());
		dataSource.setUsername(slave01Properties.getUsername());
		dataSource.setPassword(slave01Properties.getPassword());
		if (slave01Properties.getInitialSize() > 0) {
			dataSource.setInitialSize(slave01Properties.getInitialSize());
		}
		if (slave01Properties.getMinIdle() > 0) {
			dataSource.setMinIdle(slave01Properties.getMinIdle());
		}
		if (slave01Properties.getMaxActive() > 0) {
			dataSource.setMaxActive(slave01Properties.getMaxActive());
		}
		dataSource.setTestOnBorrow(slave01Properties.isTestOnBorrow());
		try {
			dataSource.init();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dataSource;
	}

	@Bean(name="slave02")
	public DataSource getSlave02DataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(slave02Properties.getDriver());
		dataSource.setUrl(slave02Properties.getUrl());
		dataSource.setUsername(slave02Properties.getUsername());
		dataSource.setPassword(slave02Properties.getPassword());
		if (slave02Properties.getInitialSize() > 0) {
			dataSource.setInitialSize(slave02Properties.getInitialSize());
		}
		if (slave02Properties.getMinIdle() > 0) {
			dataSource.setMinIdle(slave02Properties.getMinIdle());
		}
		if (slave02Properties.getMaxActive() > 0) {
			dataSource.setMaxActive(slave02Properties.getMaxActive());
		}
		dataSource.setTestOnBorrow(slave02Properties.isTestOnBorrow());
		try {
			dataSource.init();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dataSource;
	}

}
