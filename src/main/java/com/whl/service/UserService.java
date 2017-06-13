package com.whl.service;

import java.util.List;

import com.whl.domain.User;

public interface UserService extends BaseService<User> {
	public List<User> getUserList() throws Exception;

	void getSomeData();

	void insertUserTwo();

}
