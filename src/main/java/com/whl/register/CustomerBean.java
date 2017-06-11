package com.whl.register;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 * 
 * description: 可以自己加注解注入spring容器，也可以使用java配置的方式加入spring容器
 * 
 * @author whling
 * @date 2017年6月9日 下午6:16:03
 *
 */
//@Component
public class CustomerBean {
	public void getSomeData() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		for (String string : list) {
			System.out.println(string);
		}
	}
}
