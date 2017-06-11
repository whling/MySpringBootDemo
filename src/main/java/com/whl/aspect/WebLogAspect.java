package com.whl.aspect;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 
 * description: web日志记录切面，注意，切面表达式要写成controller包下（springboot引入aop的依赖包之后自动启动AOP）
 * 				这个类多线程并发访问时候，成员属性会有线程安全问题
 * @author whling
 * @date 2017年6月10日 下午8:47:33
 *
 */
@Aspect
@Component
@Order(-5)  //在切入点前的操作，按order的值由小到大执行,在切入点后的操作，按order的值由大到小执行,在实际中order值可以设置为负值，确保是第一个进行执行的。
public class WebLogAspect {
	/**
	 * 每个线程记录的开始时间放在自己的ThreadLocal中
	 */
	private ThreadLocal<Long> startTime =new ThreadLocal<Long>();
	/**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在任意包或者子包
     * ~ 第五个 * 任意方法 ~ .. 匹配任意数量的参数.
	 */
	@Pointcut(value = "execution(public * com.whl.controller..*.*(..))")
	public void WebLog() {
	}

	@Before("WebLog()")
	public void doBefore(JoinPoint joinPoint) {
		// 记录访问开始时间
		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		System.out.println("WebLogAspect.doBefore()");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		System.out.println("URL : " + request.getRequestURL().toString());
		System.out.println("HTTP_METHOD : " + request.getMethod());
		System.out.println("IP : " + request.getRemoteAddr());
		System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		// 获取所有参数方法一：
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			System.out.println(paraName + ": " + request.getParameter(paraName));
		}
	}
	/**
	 * 一旦业务方法内出错，此方法不会被执行，但是返回回去的拦截器还是会执行的
	 * @param joinPoint
	 */
	@AfterReturning("WebLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		System.out.println("WebLogAspect.doAfterReturning()");
		System.out.println("totalTime:" + (System.currentTimeMillis() - startTime.get())+"ms");
	}
}
