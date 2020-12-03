package com.xf;

import com.xf.service.MailServerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;

@SpringBootTest

class HelloworldApplicationTests {

	@Autowired
	MailServerImpl mailServer;
	@Autowired
	RedisTemplate redisTemplate;


	@Test
	void contextLoads() {
		for (int i=0;i<10;i++)
		{
			mailServer.sendSimpleMail("1193150063@qq.com","嘿嘿","sbsbsbsbsb");
		}
	}

}
