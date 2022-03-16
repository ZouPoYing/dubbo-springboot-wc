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

import java.util.HashMap;
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
}
