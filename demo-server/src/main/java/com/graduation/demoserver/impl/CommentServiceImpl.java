package com.graduation.demoserver.impl;

import com.graduation.dao.Comment;
import com.graduation.demoserver.mapper.CommentMapper;
import com.graduation.service.CommentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public List<Map<String, Object>> getCommentsByCircleId(String circleId) {
        return commentMapper.getCommentsByCircleId(circleId);
    }
}
