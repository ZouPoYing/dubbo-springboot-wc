package com.graduation.demoserver.mapper;

import com.graduation.dao.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    @Select("select * from message where (from_id = #{userId} and to_id = #{friend}) " +
            "or (from_id = #{friend} and to_id = #{userId}) order by create_time and status <> '删除'")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "fromId", column = "from_id"),
            @Result(property = "toId", column = "to_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time") })
    public List<Map<String, Object>> getMessage(String userId, String friend);

    @Insert("insert into message(from_id,to_id,type,value) values(#{fromId},#{toId},#{type},#{value})")
    @Options(useGeneratedKeys=true, keyProperty="messageId", keyColumn="message_id")
    public void insertMessage(Message message);

    @Select("select m.* from (select from_id,to_id,max(create_time) as maxtime from message where from_id = #{userId} " +
            "or to_id = #{userId} group by from_id,to_id) t inner join message m on t.from_id = m.from_id and t.to_id = " +
            "m.to_id and t.maxtime=m.create_time order by m.create_time desc")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "fromId", column = "from_id"),
            @Result(property = "toId", column = "to_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time") })
    public List<Map<String, Object>> getChatRoom(String userId);

}
