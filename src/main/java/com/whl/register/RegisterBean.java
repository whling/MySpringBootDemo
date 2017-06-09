package com.whl.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterBean {
	@Bean
	public CustomerBean getCustomerBean() {
		System.err.println("init.......register");
		return new CustomerBean();
	}
}
