package com.whl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.whl.mapper") // 如果不用此注解，那么就要在每个mapper.java上配置@mapper注解
@EnableCaching // 开启缓存
@ImportResource(value = "classpath:application-service.xml") // 引入spring的配置文件，在集成dubbo的时候用到
public class MySpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootDemoApplication.class, args);
	}
}
