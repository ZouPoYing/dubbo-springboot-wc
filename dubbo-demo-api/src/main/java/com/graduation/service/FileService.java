package com.graduation.service;

import com.graduation.dao.File;

import java.util.List;
import java.util.Map;

public interface FileService {

    public void fileUpload(String fileName, Integer userId) throws Exception;

    public File getFileByFileName(String fileName) throws Exception;

    public File getFileByFileId(Integer fileId) throws Exception;

    public List<String> getFileNameListByFileIds(String fileIds);

    List<Map<String, Object>> getUselessFiles();

    void deleteFileByFileId(String fileId);
}
