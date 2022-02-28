package com.graduation.demoserver.impl;

import com.graduation.dao.File;
import com.graduation.demoserver.mapper.FileMapper;
import com.graduation.service.FileService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
@Service
public class FileServiceImpl implements FileService {

    @Autowired(required = false)
    private FileMapper fileMapper;

    public void fileUpload(String fileName, Integer userId) throws Exception {
        fileMapper.fileUpload(fileName,userId);
    }

    public File getFileByFileName(String fileName) throws Exception {
        return fileMapper.getFileByFileName(fileName);
    }

    public File getFileByFileId(Integer fileId) throws Exception {
        return fileMapper.getFileByFileId(fileId);
    }

    @Override
    public List<String> getFileNameListByFileIds(String fileIds) {
        return fileMapper.getFileNameListByFileIds(fileIds);
    }

    @Override
    public List<Map<String, Object>> getUselessFiles() {
        return fileMapper.getUselessFiles();
    }

    @Override
    public void deleteFileByFileId(String fileId) {
        fileMapper.deleteFileByFileId(fileId);
    }

}
