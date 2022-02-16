package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "teacher")
public class Teacher {

    //唯一标识
    @TableId(type = IdType.AUTO)
    private Integer id;
    //工号
    private String worknum;
    //姓名
    private String teaname;
    //性别
    private String sex;
    //所属部门
    private String dept;
    //教师类型（辅导员/教师）
    private String type;
    //密码
    private String password;

}
