package com.example.teachingonlinebackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teachingonlinebackend.entity.FileData;
import com.example.teachingonlinebackend.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class fileUploadController {

    @Autowired
    private OssUploadService ossUploadService;

    /*
    * 用于将文件上传到OSS的接口,并将文件路径存储到数据库中
    * url:/upload/file
    * 请求方式：post
    *
    * */
    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile multipartFile,
                         Integer nodeId, Integer lessonId) throws IOException {

        System.out.println(nodeId);
        System.out.println(lessonId);
        if(multipartFile.isEmpty()){
            return "文件有误";
        }
        System.out.println(multipartFile.getOriginalFilename());

        return ossUploadService.uploadFile(multipartFile,nodeId,lessonId);
    }

    /*
    * 从数据库中获取对应任务节点下的文件
    * url:/file/getFileByNodeId
    * 请求方式：get
    *
    * */
    @GetMapping("/getFileByNodeId")
    public List<FileData> getFileByNodeId(@RequestParam Integer nodeId){
        QueryWrapper<FileData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("node_id",nodeId);
        return ossUploadService.list(queryWrapper);
    }

    /*
    * 从数据库中获取对应课程下的文件（通过lessonId查询）
    * url:/file/getFileByLessonId
    * 请求方式：get
    *
    * */
    @GetMapping("/getFileByLessonId")
    public List<FileData> getFileByLessonId(@RequestParam Integer lessonId){
        QueryWrapper<FileData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("lesson_id",lessonId).eq("node_id",-1);

        return ossUploadService.list(queryWrapper);
    }
}
