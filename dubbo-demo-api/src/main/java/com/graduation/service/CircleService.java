package com.graduation.service;

import com.graduation.dao.Circle;

import java.util.List;
import java.util.Map;

public interface CircleService {

    void insertCircle(Circle circle);

    List<Map<String, Object>> getCircle(String userId);
}
