package com.graduation.demoserver.mapper;

import com.graduation.dao.Friend;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendMapper {

    @Select("select count(1) from friend where user_id=#{userId} and friend=#{friend} and status=#{status}")
    public int queryCountByUserIdAndStatus(Friend friend);

    @Insert("insert into friend(user_id,friend,status,tips) values(#{userId},#{friend},#{status},#{tips})")
    @Options(useGeneratedKeys=true, keyProperty="friendId", keyColumn="friend_id")
    public void insertFriend(Friend friend);

    @Select("select f.friend_id as friendId,u.user_name as applyName,u.user_head as head,f.tips,f.status from " +
            "friend f left join user u on f.user_id=u.user_id  where f.friend=#{userId} and status in ('申请','拒绝','好友') order by f.create_time desc")
    public List<Map<String, Object>> getApply(String userId);

    @Update("update friend set status = #{status} where friend_id = #{friendId} ")
    public void setStatusByFriendId(String friendId, String status);

    @Select("select * from user where user_id in (select user_id from friend where status = '好友' and friend = #{userId}) " +
            "or user_id in  (select friend from friend where status = '好友' and user_id = #{userId}) order by user_name desc")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userHead", column = "user_head"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userType", column = "user_type") })
    public List<Map<String, Object>> getFriend(String userId);
}
