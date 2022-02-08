package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "student")
public class Student {

    //唯一标识 @TableId注解 标注主键 如果变量名和数据库保持一致可以不写
    @TableId
    private Integer id;
    //姓名
    private String stdname;
    //学号
    private Integer stdnum;
    //所属院系
    private String dept;
}
