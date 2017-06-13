package com.whl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.whl.domain.User;
import com.whl.service.UserService;

@RestController
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	// 注入service服务对象
	@Autowired
	private UserService userService;

	/**
	 * 获取user列表
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/user")
	public List<User> getUserList() throws Exception {
		log.debug("test controller");
		List<User> userList = userService.getUserList();
		log.debug("test successful");
		return userList;
	}

	/**
	 * 获取user
	 */
	@RequestMapping("/user/{id}")
	@ResponseBody
	public User getUserById(@PathVariable Integer id) {
		return userService.getByPrimaryKey(id);
	}

	/**
	 * 新增user
	 * 
	 * @param user
	 */
	@PostMapping("/user")
	public void insertUser(User user) {
		userService.save(user);
		System.out.println(user.getId());
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteByPrimaryKey(id);
	}

	/**
	 * 修改user
	 * 
	 * @param user
	 */
	@PutMapping("/user")
	public void updateUser(User user) {
		userService.updateSelective(user);
	}
}
