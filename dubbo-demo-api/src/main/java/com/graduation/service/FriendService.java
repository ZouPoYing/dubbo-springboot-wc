package com.graduation.service;

import com.graduation.dao.Friend;

import java.util.List;
import java.util.Map;

public interface FriendService {

    int queryCountByUserIdAndStatus(Friend friend);

    void insertFriend(Friend friend);

    List<Map<String, Object>> getApply(String userId);

    void setStatusByFriendId(String friendId, String status);

    List<Map<String, Object>> getFriend(String userId);
}
