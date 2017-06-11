package com.whl.multidatasource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @Author: Whling
 * @Date: Created in 20:58 2017/6/7
 * @Modified By:
 * @Description: 定义数据源的AOP切面，通过该Service的方法名判断是应该走读库还是写库
 */

@Aspect
@Component
@Order(-9999)
public class DataSourceAspect {
	@Pointcut(value = "execution(public * com.whl.service..*.*(..))")
	public void DSAspect() {
	}
	/**
	 * 在进入Service方法之前执行
	 * 
	 * @param point
	 *            切面对象
	 */
	@Before("DSAspect()")
	public void before(JoinPoint point) {
		// 获取到当前执行的方法名
		String methodName = point.getSignature().getName();
		if (isSlave(methodName)) {
			// 标记为读库
			DynamicDataSourceHolder.markSlave();
		} else {
			// 标记为写库
			DynamicDataSourceHolder.markMaster();
		}
	}

	/**
	 * 判断是否为读库
	 * 
	 * @param methodName
	 * @return
	 */
	private Boolean isSlave(String methodName) {
		// 方法名以query、find、get开头的方法名走从库
		return StringUtils.startsWithAny(methodName, "query", "find", "get");
	}
}
