package com.whl.customer;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * description: 只提供了常用的属性，如果有需要，自己添加
 * 
 * @author whling
 * @date 2017年6月10日 下午11:08:54
 *
 */
@ConfigurationProperties(prefix = "jdbc.slave02")
public class DruidSlave02Properties {
	private String driver;
	private String url;
	private String username;
	private String password;

	private int maxActive;
	private int minIdle;
	private int initialSize;
	private boolean testOnBorrow;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
}
