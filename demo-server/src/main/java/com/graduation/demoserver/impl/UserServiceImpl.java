package com.graduation.demoserver.impl;

import com.graduation.dao.User;
import com.graduation.demoserver.mapper.UserMapper;
import com.graduation.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0.0")
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public int queryUserCountByUserName(User user) {
        return userMapper.queryUserCountByUserName(user);
    }

    @Override
    public int queryUserCountByUserPhone(User user) {
        return userMapper.queryUserCountByUserPhone(user);
    }

    @Override
    public User queryUserByUserId(User user) {
        return userMapper.queryUserByUserId(user);
    }

    @Override
    public int queryUserCountByUserNameAndPassword(User user) {
        return userMapper.queryUserCountByUserNameAndPassword(user);
    }

    @Override
    public int queryUserCountByUserPhoneAndPassword(User user) {
        return userMapper.queryUserCountByUserPhoneAndPassword(user);
    }

    @Override
    public User queryUserByUserPhone(User user) {
        return userMapper.queryUserByUserPhone(user);
    }

    @Override
    public void updateUserByUserIdAndSub(String userId, String sub) {
        userMapper.updateUserByUserIdAndSub(userId, sub);
    }

    @Override
    public String getUserHeadByUserId(String userId) {
        return userMapper.getUserHeadByUserId(userId);
    }

    @Override
    public String getUserNameByUserId(String userId) {
        return userMapper.getUserNameByUserId(userId);
    }
}
