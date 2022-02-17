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
    private String name;
    //任课教师工号
    private String worknum;
    //任课教师名称
    private  String teaname;
    @TableField("total_std")
    private Integer total;
}
