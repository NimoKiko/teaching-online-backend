package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tea_lesson")
public class TeaLesson {
    //id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //教师工号
    private String worknum;
    //课程名称
    private String lessonName;
    //课程id
    private Integer lessonId;
    //教师名称
    private String teaname;
}
