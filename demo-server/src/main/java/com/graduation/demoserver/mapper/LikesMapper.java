package com.graduation.demoserver.mapper;

import com.graduation.dao.Likes;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LikesMapper {

    @Select("select * from likes where circle_id = #{circleId} and user_id = #{userId}")
    @Results({
            @Result(property = "likesId", column = "likes_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "circleId", column = "circle_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time") })
    public List<Map<String, Object>> getLikesByUserIdAndCircleId(String userId, String circleId);

    @Insert("insert into likes(circle_id,user_id,status) values(#{circleId},#{userId},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="likesId", keyColumn="likes_id")
    public void insertLikes(Likes likes);

    @Update("update likes set status = #{status} where circle_id = #{circleId} and user_id = #{userId} ")
    public void updateLikes(Likes likes);

    @Select("select user_name from user where user_id in (select user_id from likes where " +
            "likes.circle_id = #{circleId} and status = '点赞')")
    @Results({
            @Result(property = "userName", column = "user_name") })
    public List<String> getLikesByCircleId(String circleId);
}
