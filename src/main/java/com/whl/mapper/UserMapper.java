package com.whl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whl.domain.User;

public interface UserMapper {
	public List<User> getUserList();
}
