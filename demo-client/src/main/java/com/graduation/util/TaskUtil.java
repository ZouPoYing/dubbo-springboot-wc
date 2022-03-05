package com.graduation.util;

import com.graduation.service.FileService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TaskUtil {

    @DubboReference(version = "1.0.0")
    public FileService fileService;

    //3.添加定时任务
//    @Scheduled(cron = "0/15 * * * * ?")
    //@Scheduled(cron = "0 0 0 1/1 * ? ") //每天凌晨0点0分0秒执行一次
    private void configureTasks() throws Exception {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        deleteFile();
    }

    public void deleteFile() throws Exception {
        List<Map<String, Object>> uselessFiles = fileService.getUselessFiles();
        if (uselessFiles == null || uselessFiles.size() == 0) {
            return;
        }
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Calendar calc = Calendar.getInstance();
        calc.setTime(now);
        calc.add(calc.DATE, -30);
        Date monthAgo = calc.getTime();
        for (Map<String, Object> uselessFile:uselessFiles) {
            if (monthAgo.compareTo((Date) uselessFile.get("createTime")) > 0) {
                fileService.deleteFileByFileId(uselessFile.get("fileId").toString());
                System.err.println("执行静态定时任务时间: " + LocalDateTime.now() + "=======删除的文件fileId:" + uselessFile.get("fileId").toString());
                FileDeleteUtil.deleteFile("D:/IDEAProjects/dubbo-springboot-demo/demo-client/src/main/resources/static/" + uselessFile.get("fileName").toString());
                System.err.println("执行静态定时任务时间: " + LocalDateTime.now() + "=======删除的文件名:" + uselessFile.get("fileName").toString());
            }
        }

    }
}
