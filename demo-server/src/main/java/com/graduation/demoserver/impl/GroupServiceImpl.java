package com.graduation.demoserver.impl;

import com.graduation.dao.Group;
import com.graduation.demoserver.mapper.GroupMapper;
import com.graduation.service.GroupService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired(required = false)
    private GroupMapper groupMapper;

    @Override
    public void insertGroup(Group group) {
        groupMapper.insertGroup(group);
    }

    @Override
    public List<Map<String, Object>> getGroupRoom(String userId) {
        return groupMapper.getGroupRoom(userId);
    }

    @Override
    public Map<String, Object> getGroupDetailByGroupId(String groupId) {
        return groupMapper.getGroupDetailByGroupId(groupId);
    }

    @Override
    public void updateGroupNameByGroupId(String groupName, String groupId) {
        groupMapper.updateGroupNameByGroupId(groupName,groupId);
    }
}
