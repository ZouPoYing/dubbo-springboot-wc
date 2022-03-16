package com.graduation.service;

import com.graduation.dao.User;

public interface UserService {

    void insertUser(User user);

    int queryUserCountByUserName(User user);

    int queryUserCountByUserPhone(User user);

    User queryUserByUserId(User user);

    int queryUserCountByUserNameAndPassword(User user);

    int queryUserCountByUserPhoneAndPassword(User user);

    User queryUserByUserPhone(User user);

    void updateUserByUserIdAndSub(String userId, String sub);

    String getUserHeadByUserId(String userId);
}
