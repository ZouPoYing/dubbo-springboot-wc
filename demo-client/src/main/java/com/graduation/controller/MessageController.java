package com.graduation.controller;

import com.graduation.dao.Message;
import com.graduation.dao.User;
import com.graduation.service.FileService;
import com.graduation.service.GroupService;
import com.graduation.service.MessageService;
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
@RequestMapping("/message")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class MessageController {

    @DubboReference(version = "1.0.0")
    public MessageService messageService;

    @DubboReference(version = "1.0.0")
    public UserService userService;

    @DubboReference(version = "1.0.0")
    public FileService fileService;

    @DubboReference(version = "1.0.0")
    public GroupService groupService;

    // 获取消息
    @RequestMapping("/getMessage")
    public Map<String, Object> getMessage(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String friend = params.get("friend");
        String isFriend = params.get("isFriend");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(friend) || MyUtil.isEmpty(isFriend)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> messages;
        if ("true".equals(isFriend)) {
            messages = messageService.getMessage(userId, friend);
        } else {
            messages = messageService.getGroupMessage(userId, friend);
        }
        if (messages.size() == 0) {
            result.put("message", messages);
            result.put("success", true);
            return result;
        }
        User friendCard = new User();
        List<Map<String, Object>> messageList = new ArrayList<>();
        for (Map<String, Object> message : messages) {
            if (userId.equals(message.get("fromId").toString())) {
                message.put("mark", "from");
                message.put("userHead", userService.getUserHeadByUserId(userId));
            } else if ("true".equals(isFriend)) {
                message.put("mark", "to");
                message.put("userHead", userService.getUserHeadByUserId(friend));
            } else {
                message.put("mark", "to");
                message.put("userHead", userService.getUserHeadByUserId(message.get("fromId").toString()));
            }
            if ("friend".equals(message.get("type").toString()) ) {
                friendCard.setUserId(Integer.valueOf(message.get("value").toString()));
                User f = userService.queryUserByUserId(friendCard);
                message.put("fHead", f.getUserHead());
                message.put("fName", f.getUserName());
            } else if ("pic".equals(message.get("type").toString()) || "file".equals(message.get("type").toString())) {
                message.put("fileName", fileService.getFileByFileId(Integer.valueOf(message.get("value").toString())).getFileName());
                message.put("fileUrl", "http://localhost:8070/" + fileService.getFileByFileId(Integer.valueOf(message.get("value").toString())).getFileName());
            }
            messageList.add(message);
        }
        result.put("message", messageList);
        result.put("success", true);
        return result;
    }

    // 发送消息
    @RequestMapping("/sendMessage")
    public Map<String, Object> sendMessage(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String friend = params.get("friend");
        String value = params.get("value");
        String type = params.get("type");
        String isFriend = params.get("isFriend");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(friend) || MyUtil.isEmpty(value)
                || MyUtil.isEmpty(type) || MyUtil.isEmpty(isFriend)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Message message = new Message();
        message.setFromId(Integer.valueOf(userId));
        if ("true".equals(isFriend)) {
            message.setToId(Integer.valueOf(friend));
        } else {
            message.setGroupId(Integer.valueOf(friend));
        }
        message.setType(type);
        message.setValue(value);
        messageService.insertMessage(message);
        result.put("success", true);
        return result;
    }

    // 获取聊天室
    @RequestMapping("/getChatRoom")
    public Map<String, Object> getChatRoom(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = new User();
        List<Map<String, Object>> chatRooms = messageService.getChatRoom(userId);
        List<Map<String, Object>> groupRooms = groupService.getGroupRoom("%," + userId + ",%");
        List<Map<String, Object>> chatRoomList = new ArrayList<>();
        List<String> fromTo = new ArrayList<>();
        for (Map<String, Object> groupRoom : groupRooms) {
            user.setUserId(Integer.valueOf(groupRoom.get("userId").toString()));
            user = userService.queryUserByUserId(user);
            groupRoom.put("friend", groupRoom.get("groupId"));
            groupRoom.put("userName", user.getUserName());
            groupRoom.put("userHead", user.getUserHead());
            groupRoom.put("time", MyUtil.formatTime(groupRoom.get("createTime")));
            chatRoomList.add(groupRoom);
        }
        for (Map<String, Object> chatRoom : chatRooms) {
            if (fromTo.contains(MyUtil.getToFrom(chatRoom))) {
                continue;
            }
            if (userId.equals(chatRoom.get("fromId").toString())) {
                user.setUserId(Integer.valueOf(chatRoom.get("toId").toString()));
                chatRoom.put("mark", "from");
            } else {
                user.setUserId(Integer.valueOf(chatRoom.get("fromId").toString()));
                chatRoom.put("mark", "to");
            }
            user = userService.queryUserByUserId(user);
            chatRoom.put("friend", user.getUserId());
            chatRoom.put("userName", user.getUserName());
            chatRoom.put("userHead", user.getUserHead());
            chatRoom.put("time", MyUtil.formatTime(chatRoom.get("createTime")));
            fromTo.add(MyUtil.getFromTo(chatRoom));
            chatRoomList.add(chatRoom);
        }
        result.put("chatRoom", chatRoomList);
        result.put("success", true);
        return result;
    }
}
