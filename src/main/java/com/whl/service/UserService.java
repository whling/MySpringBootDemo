package com.whl.service;

import java.util.List;

import com.whl.domain.User;

public interface UserService extends BaseService<User> {
	public List<User> getUserList();

	void getSomeData();

	void insertUserTwo();

}
