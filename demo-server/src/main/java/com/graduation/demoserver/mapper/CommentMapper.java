package com.graduation.demoserver.mapper;

import com.graduation.dao.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(circle_id,user_id,text) values(#{circleId},#{userId},#{text})")
    @Options(useGeneratedKeys=true, keyProperty="commentId", keyColumn="comment_id")
    public void insertComment(Comment comment);

    @Select("select u.user_name,c.text from user u left join comment c on u.user_id=c.user_id where c.circle_id = #{circleId} order by c.create_time desc")
    @Results({
            @Result(property = "userName", column = "user_name") })
    public List<Map<String, Object>> getCommentsByCircleId(String circleId);
}
