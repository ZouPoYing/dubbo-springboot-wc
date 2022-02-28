package com.graduation.controller;

import com.graduation.dao.Comment;
import com.graduation.service.CommentService;
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
@RequestMapping("/comment")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class CommentController {

    @DubboReference(version = "1.0.0")
    public CommentService commentService;

    // 评论
    @RequestMapping("/comment")
    public Map<String, Object> comment(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String text = params.get("text");
        String circleId = params.get("circleId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(text) || MyUtil.isEmpty(circleId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Comment comment = new Comment();
        comment.setCircleId(Integer.valueOf(circleId));
        comment.setUserId(Integer.valueOf(userId));
        comment.setText(text);
        commentService.insertComment(comment);
        result.put("success", true);
        return result;
    }
}
