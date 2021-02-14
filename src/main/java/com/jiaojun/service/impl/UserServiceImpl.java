package com.jiaojun.service.impl;

import com.jiaojun.dao.UserDao;
import com.jiaojun.entity.User;
import com.jiaojun.service.UserService;
import com.jiaojun.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 检查相应用户名和密码的用户
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
