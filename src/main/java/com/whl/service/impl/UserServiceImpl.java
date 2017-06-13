package com.whl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.whl.async.AsyncTask;
import com.whl.customer.CustomerProperties;
import com.whl.domain.User;
import com.whl.mapper.UserMapper;
import com.whl.register.CustomerBean;
import com.whl.service.UserService;

@Service
@PropertySource(value = "classpath:jdbc.properties") //读取外部配置文件中的属性
//@Profile(value="prod")  //这个注解表示在生产环境中才会实例化
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	// 注入mapper代理对象
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CustomerBean customerBean;

	@Autowired
	private CustomerProperties customerProperties;

	@Value(value = "${jdbc.username}")
	private String username;
	
	@Autowired
	private AsyncTask asyncTask;

	/**
	 * 如果你仅仅是使用redis作为缓存，那么直接使用@Cacheable注解,并且开启缓存
	 * @throws Exception 
	 */
	// @Cacheable(value = "userList")
	public List<User> getUserList() throws Exception {
		System.out.println(username);
		System.out.println("这个方法会将查询结果放入redis缓存中");
//		List<User> list1 = userMapper.getUserList();
		// List<User> list = userMapper.selectAll();
		asyncTask.doTaskOne();
		PageHelper.startPage(0, 2);
		List<User> selectAll = userMapper.selectAll();
		asyncTask.doTaskTwo();
		System.out.println(selectAll.size());
		asyncTask.doTaskThree();
		return selectAll;
	}

	@Override
	public void getSomeData() {
		customerBean.getSomeData();
		System.out.println(customerProperties.getUsername() + "*******" + customerProperties.getAge());
	}

	@Override
	public void insertUserTwo() {
		User user = new User();
		user.setUsername("无所谓");
		user.setPassword("sorry");
		userMapper.insert(user);
		int i = 1 / 0;
		User user1 = new User();
		user1.setUsername("无所谓");
		user1.setPassword("sorry");
		userMapper.insert(user1);
	}
}
