package com.graduation.controller;

import com.graduation.dao.Friend;
import com.graduation.service.FriendService;
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
@RequestMapping("/friend")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class FriendController {

    @DubboReference(version = "1.0.0")
    public FriendService friendService;


    // 判断两人是否为好友关系
    @RequestMapping("/isFriend")
    public Map<String, Object> isFriend(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String friend = params.get("friend");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(friend) || MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Friend f = new Friend();
        f.setUserId(Integer.valueOf(userId));
        f.setFriend(Integer.valueOf(friend));
        f.setStatus("好友");
        Friend f1 = new Friend();
        f1.setUserId(Integer.valueOf(friend));
        f1.setFriend(Integer.valueOf(userId));
        f1.setStatus("好友");
        if (userId.equals(friend)) {
            result.put("isFriend", true);
            result.put("success", true);
        } else if (friendService.queryCountByUserIdAndStatus(f) == 0 && friendService.queryCountByUserIdAndStatus(f1) == 0) {
            result.put("isFriend", false);
            result.put("success", true);
        } else {
            result.put("isFriend", true);
            result.put("success", true);
        }
        return result;
    }

    // 发起好友请求
    @RequestMapping("/friendApply")
    public Map<String, Object> friendApply(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String friend = params.get("friend");
        String tips = params.get("tips");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(friend) || MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Friend f = new Friend();
        f.setUserId(Integer.valueOf(userId));
        f.setFriend(Integer.valueOf(friend));
        f.setTips(tips);
        f.setStatus("好友");
        Friend f1 = new Friend();
        f1.setUserId(Integer.valueOf(friend));
        f1.setFriend(Integer.valueOf(userId));
        f1.setStatus("好友");
        if (userId.equals(friend)) {
            result.put("msg", "不能添加你自己");
            return result;
        } else if (friendService.queryCountByUserIdAndStatus(f) > 0 || friendService.queryCountByUserIdAndStatus(f1) > 0) {
            result.put("msg", "你们已经是好友了");
            return result;
        }
        f.setStatus("申请");
        f1.setStatus("申请");
        if (friendService.queryCountByUserIdAndStatus(f) > 0) {
            result.put("msg", "你已经申请过了");
            return result;
        }
        if (friendService.queryCountByUserIdAndStatus(f1) > 0) {
            result.put("msg", "ta向你发起申请过了");
            return result;
        }
        friendService.insertFriend(f);
        result.put("success", true);
        return result;
    }

    // 获取好友申请信息
    @RequestMapping("/getApply")
    public Map<String, Object> getApply(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        result.put("apply", friendService.getApply(userId));
        result.put("success", true);
        return result;
    }

    // 接受好友申请
    @RequestMapping("/acceptByFriendId")
    public Map<String, Object> acceptByFriendId(@RequestBody Map<String, String> params) throws Exception {
        String friendId = params.get("friendId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(friendId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        friendService.setStatusByFriendId(friendId, "好友");
        result.put("success", true);
        return result;
    }

    // 拒绝好友申请
    @RequestMapping("/refuseByFriendId")
    public Map<String, Object> refuseByFriendId(@RequestBody Map<String, String> params) throws Exception {
        String friendId = params.get("friendId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(friendId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        friendService.setStatusByFriendId(friendId, "拒绝");
        result.put("success", true);
        return result;
    }

    // 获取好友列表
    @RequestMapping("/getFriend")
    public Map<String, Object> getFriend(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        result.put("friends", friendService.getFriend(userId));
        result.put("success", true);
        return result;
    }
}
