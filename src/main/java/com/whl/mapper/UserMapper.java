package com.whl.mapper;

import java.util.List;

import com.whl.domain.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	public List<User> getUserList();
}
