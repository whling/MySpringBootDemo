package com.whl.test.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.sun.tools.internal.xjc.model.SymbolSpace;
import com.whl.MySpringBootDemoApplication;
import com.whl.domain.User;
import com.whl.mapper.UserMapper;
import com.whl.service.UserService;

import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@SpringBootTest(classes = MySpringBootDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {
	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testUserser() {
		userService.getSomeData();
	}

	/**
	 * 查询
	 */
	@Test
	public void getUserList() {
		// User user = new User();
		// user.setUsername("钱七");
		// PageInfo<User> pageInfo = userService.getAll(0, 3);
		// List<User> list = pageInfo.getList();
		//
		// for (User user : list) {
		// System.out.println(user.getUsername());
		// }
		// PageInfo<User> pageInfo = userService.getPageListByWhere(user, 0, 2);
		// List<User> list = pageInfo.getList();
		// for (User user2 : list) {
		// System.out.println(user2.getScore());
		// }
		// userService.getListByExample(object);

		Example example = new Example(User.class);
		example.setOrderByClause("score desc"); // 按照score字段倒序排列
		System.out.println(example.getOrderByClause());
		example.selectProperties("score"); // 指定只查询哪个属性
		List<User> list = userService.getListByExample(example);
		for (User user2 : list) {
			System.out.println(user2.getScore());
		}
	}

	@Test
	public void getUserByWhere() {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", "钱七");
		PageInfo<User> pageInfo = userService.getPageListByExample(example, 0, 8);
		List<User> list = pageInfo.getList();
		for (User user : list) {
			System.out.println(user.getUsername());
		}
	}

	/**
	 * 插入数据,自增长主键自动返回
	 */
	@Test
	public void insertUser() {
		User user = new User();
		user.setUsername("钱七");
		user.setPassword("1243");
		user.setBirthday(new Date());
		user.setAddress("北京昌平");
		user.setScore(34F);
		Integer save = userService.save(user);
		System.out.println(user.getId());
		System.out.println(save);
	}

	/**
	 * 批量插入测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void insertList() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse("1978-08-23");
		List<User> list = new ArrayList<>();
		User user1 = new User();
		User user2 = new User();

		user1.setUsername("科比布莱恩特");
		user1.setPassword("1234");
		user1.setBirthday(date);
		user1.setScore(81F);
		user1.setAddress("Los Angeles");
		user1.setDescrible("basketball player");
		BeanUtils.copyProperties(user1, user2);

		list.add(user1);
		list.add(user2);
		int i = userMapper.insertList(list);
		System.out.println(i);
	}

	/**
	 * 删除数据
	 */
	@Test
	public void deleteUser() {
		List<Object> values = new ArrayList<>();
		values.add(23);
		values.add(24);
		// Integer integer = userService.deleteByPrimaryKey(25);
		// System.out.println(integer);
		userService.deleteByPrimaryKeys(User.class, "id", values);
	}

	/**
	 * 全部删除
	 */
	@Test
	public void deleteAll() {
		Example example = new Example(User.class);
		int i = userMapper.deleteByExample(example);
		System.out.println(i);
	}

	@Test
	public void updateUser() {
		User user = userService.getByPrimaryKey(27);
		System.out.println(user.getUsername());
		user.setUsername("jame");
		userService.update(user);
	}
}
