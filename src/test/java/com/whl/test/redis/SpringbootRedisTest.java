package com.whl.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whl.MySpringBootDemoApplication;

@SpringBootTest(classes = MySpringBootDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringbootRedisTest {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 设置一个string类型的key
	 */
	@Test
	public void testString() {
		redisTemplate.opsForValue().set("username", "whling");
		Long expire = redisTemplate.boundValueOps("username").getExpire();
		System.out.println("过期时间" + expire);
		String string = redisTemplate.opsForValue().get("username");
		System.out.println(string);
	}
}
