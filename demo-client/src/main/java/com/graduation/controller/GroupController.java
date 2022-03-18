package com.graduation.controller;

import com.graduation.dao.Group;
import com.graduation.dao.User;
import com.graduation.service.GroupService;
import com.graduation.service.UserService;
import com.graduation.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class GroupController {

    @DubboReference(version = "1.0.0")
    public GroupService groupService;

    @DubboReference(version = "1.0.0")
    public UserService userService;

    // 建群
    @RequestMapping("/createGroup")
    public Map<String, Object> createGroup(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String friends = params.get("friends");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(friends)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        user = userService.queryUserByUserId(user);
        Group group = new Group();
        group.setUserId(Integer.valueOf(userId));
        group.setFriends("," + userId + "," + friends + ",");
        group.setGroupName(user.getUserName() + "发起的群聊");
        groupService.insertGroup(group);
        result.put("success", true);
        return result;
    }

    // 获取群详情
    @RequestMapping("/getGroupDetail")
    public Map<String, Object> getGroupDetail(@RequestBody Map<String, String> params) throws Exception {
        String groupId = params.get("groupId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(groupId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> friendDetails = new ArrayList<>();
        Map<String, Object> group = groupService.getGroupDetailByGroupId(groupId);
        String[] friends = group.get("friends").toString().substring(1,group.get("friends").toString().length()-1).split(",");
        for (String friend : friends) {
            Map<String, Object> friendDetail = new HashMap<>();
            friendDetail.put("friendHead", userService.getUserHeadByUserId(friend));
            friendDetail.put("friendName", userService.getUserNameByUserId(friend));
            friendDetails.add(friendDetail);
        }
        group.put("friendDetails", friendDetails);
        result.put("group", group);
        result.put("success", true);
        return result;
    }

    // 更新群名称
    @RequestMapping("/updateGroupName")
    public Map<String, Object> updateGroupName(@RequestBody Map<String, String> params) throws Exception {
        String groupId = params.get("groupId");
        String groupName = params.get("groupName");
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(groupId) || MyUtil.isEmpty(groupName) || MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Map<String, Object> group = groupService.getGroupDetailByGroupId(groupId);
        if (!userId.equals(group.get("userId").toString())) {
            result.put("msg", "只有群主才能修改群名称");
            return result;
        }
        groupService.updateGroupNameByGroupId(groupName,groupId);
        result.put("success", true);
        return result;
    }
}
