package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 向redis发布消息
 * @author wangll
 *
 */
public class MessagePublish {
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	public void publishMessage(String chancel, String message) {
		stringRedisTemplate.convertAndSend(chancel,message);
	}

}
