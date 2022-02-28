package com.graduation.demoserver.impl;

import com.graduation.dao.Circle;
import com.graduation.demoserver.mapper.CircleMapper;
import com.graduation.service.CircleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class CircleServiceImpl implements CircleService {

    @Autowired(required = false)
    private CircleMapper circleMapper;


    @Override
    public void insertCircle(Circle circle) {
        circleMapper.insertCircle(circle);
    }

    @Override
    public List<Map<String, Object>> getCircle(String userId) {
        return circleMapper.getCircle(userId);
    }
}
