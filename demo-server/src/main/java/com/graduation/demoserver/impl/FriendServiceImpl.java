package com.graduation.demoserver.impl;

import com.graduation.dao.Friend;
import com.graduation.demoserver.mapper.FriendMapper;
import com.graduation.service.FriendService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired(required = false)
    private FriendMapper friendMapper;

    @Override
    public int queryCountByUserIdAndStatus(Friend friend) {
        return friendMapper.queryCountByUserIdAndStatus(friend);
    }

    @Override
    public void insertFriend(Friend friend) {
        friendMapper.insertFriend(friend);
    }

    @Override
    public List<Map<String, Object>> getApply(String userId) {
        return friendMapper.getApply(userId);
    }

    @Override
    public void setStatusByFriendId(String friendId, String status) {
        friendMapper.setStatusByFriendId(friendId,status);
    }

    @Override
    public List<Map<String, Object>> getFriend(String userId) {
        return friendMapper.getFriend(userId);
    }
}
