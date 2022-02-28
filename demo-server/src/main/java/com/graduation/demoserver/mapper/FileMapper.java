package com.graduation.demoserver.mapper;

import com.graduation.dao.File;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILE(FILE_NAME, USER_ID) VALUES (#{fileName}, #{userId})")
    void fileUpload(String fileName, Integer userId);

    @Select("SELECT * FROM FILE WHERE FILE_NAME=#{fileName}")
    @Results({
            @Result(property = "fileId", column = "file_id"),
            @Result(property = "fileName", column = "file_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userId", column = "user_id") })
    File getFileByFileName(String fileName);

    @Select("SELECT * FROM FILE WHERE FILE_ID=#{fileId}")
    @Results({
            @Result(property = "fileId", column = "file_id"),
            @Result(property = "fileName", column = "file_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userId", column = "user_id") })
    File getFileByFileId(Integer fileId);

    @Delete("delete from FILE WHERE FILE_ID=#{fileId}")
    void deleteFileByFileId(String fileId);

    @Select("select concat('http://localhost:8070/',file_name) as file_name from file where file_id in " +
            "(SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(#{fileIds},',',help_topic_id+1),',',-1) AS num " +
            "FROM mysql.help_topic WHERE help_topic_id < LENGTH(#{fileIds})-LENGTH(REPLACE(#{fileIds},',',''))+1)")
    @Results({
            @Result(property = "fileName", column = "file_name") })
    List<String> getFileNameListByFileIds(String fileIds);

    @Select("select * from file where file_id not in (select file_id from file where " +
            "file_id in (select value from message where type in ('pic','file')) " +
            "or file_id in (SELECT SUBSTRING_INDEX(SUBSTRING_INDEX((select GROUP_CONCAT(pic) " +
            "from circle where pic <> ''),',',help_topic_id+1),',',-1) AS num FROM mysql.help_topic " +
            "WHERE help_topic_id < LENGTH((select GROUP_CONCAT(pic) from circle where pic <> ''))" +
            "-LENGTH(REPLACE((select GROUP_CONCAT(pic) from circle where pic <> ''),',',''))+1) " +
            "or file_name in (select SUBSTRING(user_head, 23) from user) " +
            "or file_name in (select SUBSTRING(back, 23) from user))")
    @Results({
            @Result(property = "fileId", column = "file_id"),
            @Result(property = "fileName", column = "file_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userId", column = "user_id") })
    List<Map<String, Object>> getUselessFiles();
}
