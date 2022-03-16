package com.graduation.service;

import com.graduation.dao.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    List<Map<String, Object>> getMessage(String userId, String friend);

    List<Map<String, Object>> getGroupMessage(String userId, String friend);

    void insertMessage(Message message);

    List<Map<String, Object>> getChatRoom(String userId);
}
