package com.graduation.demoserver.impl;

import com.graduation.dao.Message;
import com.graduation.demoserver.mapper.MessageMapper;
import com.graduation.service.MessageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired(required = false)
    private MessageMapper messageMapper;

    @Override
    public List<Map<String, Object>> getMessage(String userId, String friend) {
        return messageMapper.getMessage(userId,friend);
    }

    @Override
    public void insertMessage(Message message) {
        messageMapper.insertMessage(message);
    }

    @Override
    public List<Map<String, Object>> getChatRoom(String userId) {
        return messageMapper.getChatRoom(userId);
    }
}
