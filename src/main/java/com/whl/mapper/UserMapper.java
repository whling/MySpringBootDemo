package com.whl.mapper;

import java.util.List;

import com.whl.domain.User;
import com.whl.util.mapper.MyMapper;

public interface UserMapper extends MyMapper<User> {
	public List<User> getUserList();
}
