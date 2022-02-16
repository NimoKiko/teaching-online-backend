package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "student")
public class Student {

    //唯一标识 @TableId注解 标注主键 如果变量名和数据库保持一致可以不写
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学号
    private String stdnum;
    //姓名
    private String stdname;
    //性别
    private String sex;
    //所属院系
    private String dept;
    //角色类别
    private String type;
    //登陆密码
    private String password;
}
