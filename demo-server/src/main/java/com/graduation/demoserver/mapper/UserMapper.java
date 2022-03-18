package com.graduation.demoserver.mapper;

import com.graduation.dao.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user(user_name,user_phone,user_head,password,user_type,back) " +
            "values(#{userName},#{userPhone},#{userHead},#{password},#{userType},#{back})")
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="user_id")
    public void insertUser(User user);

    @Select("select count(1) from user where user_name=#{userName}")
    public int queryUserCountByUserName(User user);

    @Select("select count(1) from user where user_phone=#{userPhone}")
    public int queryUserCountByUserPhone(User user);

    @Select("select * from user where user_id=#{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userHead", column = "user_head"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userType", column = "user_type") })
    public User queryUserByUserId(User user);

    @Select("select count(1) from user where user_name=#{userName} and password=#{password}")
    public int queryUserCountByUserNameAndPassword(User user);

    @Select("select count(1) from user where user_phone=#{userPhone} and password=#{password}")
    public int queryUserCountByUserPhoneAndPassword(User user);

    @Select("select * from user where user_phone=#{userPhone}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userHead", column = "user_head"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userType", column = "user_type") })
    public User queryUserByUserPhone(User user);

    @Update("update user set ${sub} where user_id = #{userId} ")
    public void updateUserByUserIdAndSub(String userId, String sub);

    @Select("select user_head as userHead from user where user_id=#{userId}")
    public String getUserHeadByUserId(String userId);

    @Select("select user_name as userHead from user where user_id=#{userId}")
    public String getUserNameByUserId(String userId);
}
