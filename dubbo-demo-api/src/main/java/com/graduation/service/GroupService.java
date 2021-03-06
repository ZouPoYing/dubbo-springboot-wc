package com.graduation.service;

import com.graduation.dao.Group;

import java.util.List;
import java.util.Map;

public interface GroupService {

    void insertGroup(Group group);

    List<Map<String, Object>> getGroupRoom(String userId);

    Map<String, Object> getGroupDetailByGroupId(String groupId);

    void updateGroupNameByGroupId(String groupName, String groupId);
}
