package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("message")
public class Message {

    //消息编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //发布教师名称
    private String teaname;
    //教师工号
    private  String worknum;
    //作业节点id
    private Integer nodeId;
    //课程id
    private Integer lessonId;
    //课程名称
    private String lessonName;
    //消息标题
    private String title;
    //消息内容
    private String content;
    //课程类型
    private String type;
    //创建时间
    private String createTime;
}
