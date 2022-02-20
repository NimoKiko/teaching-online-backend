package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.security.PrivateKey;

@Data
@TableName("task")
public class Task {

    //节点id
    @TableField("node_id")
    private Integer nodeId;
    //作业序号
    @TableId(value = "task_id",type = IdType.AUTO)
    private  Integer taskId;
    //题干
    private String text;
    //A选项
    private String A;
    //B选项
    private String B;
    //C选项
    private String C;
    //D选项
    private String D;
    //正确答案
    private  String correct;
}
