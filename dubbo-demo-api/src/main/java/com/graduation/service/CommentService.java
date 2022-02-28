package com.graduation.service;

import com.graduation.dao.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    public void insertComment(Comment comment);

    public List<Map<String, Object>> getCommentsByCircleId(String circleId);
}
