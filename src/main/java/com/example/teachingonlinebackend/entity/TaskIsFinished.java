package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("task_isfinished")
public class TaskIsFinished {
    //id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学生学号
    private String stdnum;
    //作业编号
    private Integer nodeId;
    //完成情况
    private Integer isFinished;
}
