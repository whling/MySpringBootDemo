package com.whl.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * description: 配置自定义拦截器 ，WebMvcConfigurerAdapter里面可以重写很多方法
 * 
 * @author whling
 * @date 2017年6月7日 上午11:15:35
 *
 */
@Configuration // 声明这是一个配置文件
public class CustomerInterceptorConfiguration extends WebMvcConfigurerAdapter {
	/**
	 * 只有经过DispatcherServlet 的请求，才会走拦截器链
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CustomerInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new CustomerInterceptor2()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
