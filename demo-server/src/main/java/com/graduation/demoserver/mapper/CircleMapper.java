package com.graduation.demoserver.mapper;

import com.graduation.dao.Circle;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CircleMapper {

    @Insert("insert into circle(user_id,text,pic) values(#{userId},#{text},#{pic})")
    @Options(useGeneratedKeys=true, keyProperty="circleId", keyColumn="circle_id")
    public void insertCircle(Circle circle);

    @Select("select c.*,u.user_name,u.user_head from circle c left join user u on u.user_id=c.user_id " +
            "where u.user_id in (select user_id from friend where status = '好友' and friend = #{userId}) or u.user_id in  " +
            "(select friend from friend where status = '好友' and user_id = #{userId}) or u.user_id = #{userId} order by c.create_time desc")
    @Results({
            @Result(property = "circleId", column = "circle_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userHead", column = "user_head"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time") })
    public List<Map<String, Object>> getCircle(String userId);
}
