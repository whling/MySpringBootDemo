package com.whl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.whl.mapper") // 如果不用此注解，那么就要在每个mapper.java上配置@mapper注解
@EnableCaching // 开启缓存
@ImportResource(value = "classpath:application-service.xml") // 引入spring的配置文件，在集成dubbo的时候用到
// @ActiveProfiles("dev")
@EnableAsync //启用异步调用
//@EnableScheduling //启动定时任务
public class MySpringBootDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MySpringBootDemoApplication.class, args);
		String[] names = context.getBeanDefinitionNames();
		System.out.println("所有bean的个数：" + names.length);
//		for (String string : names) {
//			System.out.println("bean的name:" + string);
//		}
		//获取spring容器中有service注解的类
		String[] annotation = context.getBeanNamesForAnnotation(Service.class);
		for (String string : annotation) {
			System.out.println(string);
		}
	}
}
