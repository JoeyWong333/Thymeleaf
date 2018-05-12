/**
 * 
 */
package com.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.model.User;

/**
 * 
 * @author wangll
 *
 */
@Component
public class UserSessionManger {
	private Logger logger = LoggerFactory.getLogger(UserSessionManger.class);
	public static Map<String, User> userInfoMap = new HashMap<String, User>();
	private int USER_SESSION_TIME_OUT = 30;//默认30分钟
	@Autowired
	private Environment environment;
	
	//定时一分钟清理一次Map
	@Scheduled(fixedRate = 60 * 1000)
	public void checkTimeOut() {
		try {
			String time = environment.getProperty("session.timeout");
			if(!StringUtils.isEmpty(time)) {
				USER_SESSION_TIME_OUT = Integer.parseInt(time);
			}
			Set<String> timeOutSet = new HashSet<String>();
			for (String key : userInfoMap.keySet()) {
				User loginUser = userInfoMap.get(key);
				long lastUpdateTime = loginUser.getLastUpdateTime();
				if ((System.currentTimeMillis() - lastUpdateTime) >= USER_SESSION_TIME_OUT*60*1000) {
					timeOutSet.add(key);
				}
			}

			for (String key : timeOutSet) {
				User loginUser = userInfoMap.get(key);
				userInfoMap.remove(key);
				// 清楚该登录用户的查询数据
				logger.info("清除超时登录用户，sessionId：" + key + ", username: " + loginUser.getUserName());
				//System.out.println("清楚超时用户"+loginApps.getEmail());
			}
		} catch (Exception e) {
			logger.error("检查会话超时出错", e);
		}
	}
	
	public static void add(String sessionId, User user) {
		user.setLastUpdateTime(System.currentTimeMillis());
		userInfoMap.put(sessionId, user);
	}

	public static User get(String sessionId){
		User user = userInfoMap.get(sessionId);
		if (user != null) {
			user.setLastUpdateTime(System.currentTimeMillis());
		}
		return user;
	}
}
