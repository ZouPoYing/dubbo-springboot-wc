package com.graduation.demoserver.impl;

import com.graduation.dao.Likes;
import com.graduation.demoserver.mapper.LikesMapper;
import com.graduation.service.LikesService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class LikesServiceImpl implements LikesService {

    @Autowired(required = false)
    private LikesMapper likesMapper;

    @Override
    public List<Map<String, Object>> getLikesByUserIdAndCircleId(String userId, String circleId) {
        return likesMapper.getLikesByUserIdAndCircleId(userId,circleId);
    }

    @Override
    public void insertLikes(Likes likes) {
        likesMapper.insertLikes(likes);
    }

    @Override
    public void updateLikes(Likes likes) {
        likesMapper.updateLikes(likes);
    }

    @Override
    public List<String> getLikesByCircleId(String circleId) {
        return likesMapper.getLikesByCircleId(circleId);
    }
}
