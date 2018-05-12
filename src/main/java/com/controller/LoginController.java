package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.UserSessionManger;
import com.model.User;
import com.utils.MD5;

/**
 * 登陆
 * @author wangll
 *
 */
@Controller
public class LoginController {
	
	//入口
    @RequestMapping(value = "/index")
    public String home() {
        return "login";
    }
    
    /**
	 * 登录
	 * @param request 
	 * @param uname 账号
	 * @param passwd 密码
	 * @return json数据
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap mp, @RequestParam String userName,@RequestParam String passwd) {
		String sessionId = request.getSession().getId();
		if(userName == null || StringUtils.isEmpty(userName)) {
			mp.addAttribute("message", "用户名为空");
			return "redirect:index";
		}
		if(passwd == null || StringUtils.isEmpty(passwd)) {
			mp.addAttribute("message", "密码为空");
			return "redirect:index";
		}
		if(userName.equals("test") && MD5.md5(passwd+"12345").equals("c06db68e819be6ec3d26c6038d8e8d1f")) {
			 mp.addAttribute("message", "登录成功");
			 User loginUser = new User(userName, passwd);
			 UserSessionManger.add(sessionId, loginUser); //加入缓存
			 return "redirect:/userlist";
		}else {
			return "redirect:index";
		}
	}
	
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String userList(HttpServletRequest request,ModelMap mp) {
		List<User> userList = new ArrayList<User>();
		userList.add(new User("test","test"));
		userList.add(new User("test1","test1"));
		userList.add(new User("test2","test2"));
		userList.add(new User("test3","test3"));
		mp.addAttribute("userlist", userList);
		return "userList";
	}

}
