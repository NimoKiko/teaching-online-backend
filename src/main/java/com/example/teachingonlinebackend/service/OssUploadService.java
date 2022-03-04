package com.example.teachingonlinebackend.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.FileData;
import com.example.teachingonlinebackend.mapper.FileDataMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssUploadService extends ServiceImpl<FileDataMapper, FileData> {

    @Autowired
    private FileDataMapper fileDataMapper;

    public String uploadFile(MultipartFile multipartFile, Integer nodeId, Integer lessonId) throws IOException {

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tBNrAfLEXVsjGTPYj2g";
        String accessKeySecret = "tcHz7ABc5Qfae8h1KQCnnPHbiy4XSN";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "finalprojectbucket";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            //获取文件上传的流
            InputStream inputStream = multipartFile.getInputStream();

            //获取文件名
            String originname = multipartFile.getOriginalFilename();
            String filename = UUID.randomUUID().toString();
            //获取文件后缀
            String suffix = originname.substring(originname.lastIndexOf("."));
            String newName = filename + suffix;
            String fileUrl = "";
            String fileType = "";
            if(suffix.equals(".pdf")){
                fileUrl = "final/pdf/" + newName;
                fileType = "pdf";
            }
            if(suffix.equals(".docx") || suffix.equals(".doc")){
                fileUrl = "final/word/" + newName;
                fileType = "word";
            }
            if(suffix.equals(".png") || suffix.equals(".jpg")){
                fileUrl = "final/pic/" + newName;
                fileType = "img";
            }
            ossClient.putObject(bucketName, fileUrl, inputStream);

            String result = "https://" + bucketName + "." + endpoint + "/" + fileUrl;

            boolean isSave = saveFile(nodeId,lessonId,fileType,originname,result);

            System.out.println(isSave);

            return result;
        } catch (Exception oe) {
            oe.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return "seccess";
    }

    public boolean saveFile(Integer nodeId,Integer lessonId,String fileType,String fileName,String fileUrl){

        FileData fileData = new FileData();

        fileData.setLessonId(lessonId);
        fileData.setNodeId(nodeId);
        fileData.setFileName(fileName);
        fileData.setFileUrl(fileUrl);
        fileData.setFileType(fileType);

        return save(fileData);

    }
}
