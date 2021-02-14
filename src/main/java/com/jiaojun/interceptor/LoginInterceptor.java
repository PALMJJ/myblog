package com.jiaojun.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录过滤拦截器类
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 前置处理
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return 布尔值
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断session里是否有用户，没有的话重定向到登录页面，给拦截掉
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
