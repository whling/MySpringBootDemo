package com.whl.customer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * description: 自定义属性配置类，配置放在application.yml中
 * 
 * @author whling
 * @date 2017年6月9日 下午5:41:33
 *
 */
// @Configuration 这个类如果想要被注入spring容器，可以添加这个注解，现在注释掉，配置在application-service.xml中
@ConfigurationProperties(prefix = "customer")
public class CustomerProperties {
	private String username;
	private Integer age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
