package com.graduation.demoserver.mapper;

import com.graduation.dao.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GroupMapper {

    @Insert("insert into wc.group(user_id,friends,group_name) values(#{userId},#{friends},#{groupName})")
    @Options(useGeneratedKeys=true, keyProperty="groupId", keyColumn="group_id")
    public void insertGroup(Group group);

    @Select("select * from wc.group g where friends like #{userId} order by g.create_time desc")
    @Results({
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time") })
    public List<Map<String, Object>> getGroupRoom(String userId);

    @Select("select * from wc.group where group_id = #{groupId}")
    @Results({
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time") })
    public Map<String, Object> getGroupDetailByGroupId(String groupId);

    @Update("update wc.group set group_name = #{groupName} where group_id = #{groupId})")
    public void updateGroupNameByGroupId(String groupName, String groupId);

}
