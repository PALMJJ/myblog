package com.jiaojun.dao;

import com.jiaojun.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层接口
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
