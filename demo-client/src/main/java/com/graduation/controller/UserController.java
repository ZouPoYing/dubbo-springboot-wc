package com.graduation.controller;

import com.graduation.dao.File;
import com.graduation.dao.User;
import com.graduation.service.FileService;
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
@RequestMapping("/user")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class UserController {

    @DubboReference(version = "1.0.0")
    public UserService userService;

    @DubboReference(version = "1.0.0")
    public FileService fileService;

    // 登录
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params) {
        String password = params.get("password");
        String userPhone = params.get("userPhone");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(password) || MyUtil.isEmpty(userPhone)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = new User();
        user.setUserPhone(userPhone);
        user.setPassword(password);
        if (userService.queryUserCountByUserPhoneAndPassword(user) < 1) {
            result.put("msg", "手机号或密码错误");
            return result;
        }
        result.put("success", true);
        result.put("user", userService.queryUserByUserPhone(user));
        return result;
    }

    // 注册
    @RequestMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> params) {
        String password = params.get("password");
        String userName = params.get("userName");
        String userPhone = params.get("userPhone");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(password) || MyUtil.isEmpty(userName) || MyUtil.isEmpty(userPhone)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (!MyUtil.isPhone(userPhone)) {
            result.put("msg", "手机号码格式不正确");
            return result;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setPassword(password);
        user.setUserHead("http://localhost:8070/4_1645892990173_default-head.png");
        user.setBack("http://localhost:8070/4_1646038916852_back.jpg");
        user.setUserType("普通");
        int userCount = userService.queryUserCountByUserPhone(user);
        if (userCount > 0) {
            result.put("msg", "该手机号已经注册");
            return result;
        }
        userService.insertUser(user);
        result.put("success", true);
        return result;
    }

    // 根据userid获取用户信息
    @RequestMapping("/getMyInfoByUserId")
    public Map<String, Object> getMyInfoByUserId(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        result.put("user", userService.queryUserByUserId(user));
        result.put("success", true);
        return result;
    }

    // 根据userid修改用户信息
    @RequestMapping("/change")
    public Map<String, Object> change(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        String type = params.get("type");
        String msg = params.get("msg");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(type) || MyUtil.isEmpty(msg)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        userService.updateUserByUserIdAndSub(userId, MyUtil.changeSub(type,msg));
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        result.put("user", userService.queryUserByUserId(user));
        result.put("success", true);
        return result;
    }

    // 根据userid修改用户头像
    @RequestMapping("/head")
    public Map<String, Object> head(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String fileId = params.get("fileId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(fileId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        File file = fileService.getFileByFileId(Integer.parseInt(fileId));
        userService.updateUserByUserIdAndSub(userId, " user_head = 'http://localhost:8070/" + file.getFileName() + "'");
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        result.put("user", userService.queryUserByUserId(user));
        result.put("success", true);
        return result;
    }

    // 根据userid修改用户朋友圈背景
    @RequestMapping("/back")
    public Map<String, Object> back(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String fileId = params.get("fileId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(fileId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        File file = fileService.getFileByFileId(Integer.parseInt(fileId));
        userService.updateUserByUserIdAndSub(userId, " back = 'http://localhost:8070/" + file.getFileName() + "'");
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        result.put("user", userService.queryUserByUserId(user));
        result.put("success", true);
        return result;
    }

    // 根据userId 和 手机号 查找用户
    @RequestMapping("/findUser")
    public Map<String, Object> findUser(@RequestBody Map<String, String> params) throws Exception {
        String friend = params.get("friend");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(friend)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (!MyUtil.isNum(friend)) {
            result.put("msg", "格式错误");
            return result;
        }
        User user = new User();
        User u = new User();
        if (friend.length() > 10) {
            user.setUserPhone(friend);
            u = userService.queryUserByUserPhone(user);
        } else {
            user.setUserId(Integer.valueOf(friend));
            u = userService.queryUserByUserId(user);
        }
        if (u == null && userService.queryUserCountByUserPhone(user) == 0) {
            result.put("success", false);
            return result;
        }
        result.put("user", u);
        result.put("success", true);
        return result;
    }
}
