package com.whl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.whl.domain.User;
import com.whl.mapper.UserMapper;
import com.whl.service.UserService;

@Service
@PropertySource(value = "classpath:jdbc.properties")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	// 注入mapper代理对象
	@Autowired
	private UserMapper userMapper;

	@Value(value = "${jdbc.username}")
	private String username;

	/**
	 * 如果你仅仅是使用redis作为缓存，那么直接使用@Cacheable注解,并且开启缓存
	 */
	// @Cacheable(value = "userList")
	public List<User> getUserList() {
		System.out.println(username);
		System.out.println("这个方法会将查询结果放入redis缓存中");
		List<User> list1 = userMapper.getUserList();
		// List<User> list = userMapper.selectAll();
		PageHelper.startPage(0, 2);
		List<User> selectAll = userMapper.selectAll();
		System.out.println(selectAll.size());
		return selectAll;
	}

}
