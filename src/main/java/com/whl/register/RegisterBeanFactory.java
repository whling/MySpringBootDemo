package com.whl.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * description: 通过java配置的方式 将CustomerBean实例化放入spring容器
 * @author whling
 * @date 2017年6月10日 下午8:48:44
 *
 */
@Configuration
public class RegisterBeanFactory {
	@Bean
	public CustomerBean getCustomerBean() {
		System.err.println("使用java配置的方式初始化类");
		return new CustomerBean();
	}
}
