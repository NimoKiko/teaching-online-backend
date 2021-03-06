package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "std_lesson")
public class StdLesson {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //课程序号
    @TableField("lesson_id")
    private Integer lessonId;
    //课程名称
    @TableField("lesson_name")
    private String lessonName;
    //任课教师
    private String teaname;
    //学生学号
    private String stdnum;
    //学生姓名
    private  String stdname;
    //学生成绩
    private Integer score;
    //签到次数
    private Integer signTimes;
}
