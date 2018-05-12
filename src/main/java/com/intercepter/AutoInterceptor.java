package com.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.UserSessionManger;
import com.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @auth wangll
 */
public class AutoInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(AutoInterceptor.class);
	public static final String loginUrl = "/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    	String requestUrl ="";
//    	String res=request.getRequestURI();
//    	String contextPath = request.getContextPath();
//		if(res.startsWith(contextPath)){
//			requestUrl= res.substring(5,res.length() );
//		}
//		if (!requestUrl.endsWith("/")) {
//			requestUrl = requestUrl + "/";
//		}
//		// 因此对login情况做排除
//		if (requestUrl.indexOf(loginUrl) > -1) {
//			return true;
//		}
    	
    	String sessionId = request.getSession().getId();
		if (sessionId == null) {
			return false;
		}
		User loginUser = UserSessionManger.get(sessionId);
		if (loginUser == null) {
			logger.info("用户没有登录");
			response.sendRedirect(request.getContextPath()+"/index");
			return false;
		}
        return true;
    }
}
