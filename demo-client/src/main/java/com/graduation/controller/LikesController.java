package com.graduation.controller;

import com.graduation.dao.Likes;
import com.graduation.service.LikesService;
import com.graduation.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/likes")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class LikesController {

    @DubboReference(version = "1.0.0")
    public LikesService likesService;

    // 点赞
    @RequestMapping("/likes")
    public Map<String, Object> likes(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String circleId = params.get("circleId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(circleId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> likes = likesService.getLikesByUserIdAndCircleId(userId, circleId);
        Likes like = new Likes();
        like.setCircleId(Integer.valueOf(circleId));
        like.setUserId(Integer.valueOf(userId));
        if (likes == null || likes.size() == 0) {
            like.setStatus("点赞");
            likesService.insertLikes(like);
        } else {
            if ("点赞".equals(likes.get(0).get("status").toString())) {
                like.setStatus("取消点赞");
            } else {
                like.setStatus("点赞");
            }
            likesService.updateLikes(like);
        }
        result.put("success", true);
        return result;
    }
}
