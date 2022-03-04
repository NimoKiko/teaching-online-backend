package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lesson_file")
public class FileData {

    //唯一标识id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //课程id
    private Integer lessonId;
    //任务节点id
    private Integer nodeId;
    //文件名称
    private String fileName;
    //文件路径
    private String fileUrl;
    //文件类型
    private  String fileType;
}
