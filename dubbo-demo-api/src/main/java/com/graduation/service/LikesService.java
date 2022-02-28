package com.graduation.service;

import com.graduation.dao.Likes;

import java.util.List;
import java.util.Map;

public interface LikesService {

    public List<Map<String, Object>> getLikesByUserIdAndCircleId(String userId, String circleId);

    public void insertLikes(Likes likes);

    public void updateLikes(Likes likes);

    public List<String> getLikesByCircleId(String circleId);
}
