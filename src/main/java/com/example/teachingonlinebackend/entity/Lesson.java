package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "lesson")
public class Lesson {

    @TableId(value = "lesson_id",type = IdType.AUTO)
    private Integer id;
    //课程名称
    @TableField("lesson_name")
    private String lessonname;
    //任课教师工号
    private String worknum;
    //任课教师名称
    private  String teaname;
    @TableField("total_std")
    private Integer total;
    //邀请码
    private String inviteCode;
    //课程是否结束
    private Integer isEnd;
    //课程周次
    private Integer totalWeek;
    //是否正在上课
    private Integer onClass;
}
