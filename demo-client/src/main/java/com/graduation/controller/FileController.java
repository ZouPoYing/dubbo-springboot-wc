package com.graduation.controller;

import com.graduation.service.FileService;
import com.graduation.util.FileDownLoad;
import com.graduation.util.FileUploadUtil;
import com.graduation.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Slf4j
@CrossOrigin(allowCredentials ="true")
public class FileController {

    @DubboReference(version = "1.0.0")
    public FileService fileService;

    @RequestMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userId")  String userId) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if(MyUtil.isEmpty(userId)){
            result.put("msg", "参数不能为空");
            return result;
        }
        String fileName = FileUploadUtil.upload(multipartFile,userId);
        fileService.fileUpload(fileName,Integer.valueOf(userId));
        result.put("success", true);
        result.put("fileId", fileService.getFileByFileName(fileName).getFileId().toString());
        return result;
    }

    @GetMapping("/downloadFile/{fileName}")
    public void downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        //1.获取文件绝对路径
        String path = "D:/IDEAProjects/dubbo-springboot-demo/demo-client/src/main/resources/static/" + fileName;
        //2.通过绝对路径定义File
        File f = new File(path);
        //3.调用FileUtil下载文件
        FileDownLoad.downloadFile(request,response,f,false);
    }
}
