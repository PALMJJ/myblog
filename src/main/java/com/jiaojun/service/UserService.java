package com.jiaojun.service;

import com.jiaojun.entity.User;

/**
 * 用户业务层接口
 */
public interface UserService {
    /**
     * 核对用户名和密码
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User checkUser(String username, String password);
}
