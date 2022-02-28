package com.graduation.controller;

import com.graduation.dao.Circle;
import com.graduation.service.CircleService;
import com.graduation.service.CommentService;
import com.graduation.service.FileService;
import com.graduation.service.LikesService;
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
@RequestMapping("/circle")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class CircleController {

    @DubboReference(version = "1.0.0")
    public CircleService circleService;

    @DubboReference(version = "1.0.0")
    public FileService fileService;

    @DubboReference(version = "1.0.0")
    public LikesService likesService;

    @DubboReference(version = "1.0.0")
    public CommentService commentService;

    // 发朋友圈
    @RequestMapping("/sendCircle")
    public Map<String, Object> sendCricle(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String text = params.get("text");
        String pic = params.get("pic");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(text)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Circle circle = new Circle();
        circle.setUserId(Integer.valueOf(userId));
        circle.setText(text);
        circle.setPic(pic);
        circleService.insertCircle(circle);
        result.put("success", true);
        return result;
    }

    // 获取朋友圈
    @RequestMapping("/getCircle")
    public Map<String, Object> getCircle(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> circles = circleService.getCircle(userId);
        if (circles.size() == 0) {
            result.put("circle", circles);
            result.put("success", true);
            return result;
        }
        List<Map<String, Object>> circleList = new ArrayList<>();
        for (Map<String, Object> circle : circles) {
            circle.put("comments", commentService.getCommentsByCircleId(circle.get("circleId").toString()));
            circle.put("likes", likesService.getLikesByCircleId(circle.get("circleId").toString()));
            circle.put("pics", fileService.getFileNameListByFileIds(circle.get("pic").toString()));
            circle.put("time", MyUtil.formatTime(circle.get("createTime")));
            circleList.add(circle);
        }
        result.put("circle", circleList);
        result.put("success", true);
        return result;
    }
}
