package com.example.teachingonlinebackend.controller.dto;

import lombok.Data;
/*
* 接收前端登录请求的参数
* */
@Data
public class UserDto {
    //用户名
    private String worknum;
    //密码
    private String password;
}
