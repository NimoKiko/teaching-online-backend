package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.controller.dto.UserDto;
import com.example.teachingonlinebackend.entity.Teacher;
import com.example.teachingonlinebackend.mapper.TeacherMapper;
import com.example.teachingonlinebackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    //添加或修改教师信息
    public boolean saveTea(Teacher teacher) {
        return saveOrUpdate(teacher);
    }

    @Override
    public String login(UserDto userDto) {
        String worknum = userDto.getWorknum();
        String password = userDto.getPassword();

        System.out.println(worknum);
        String result = null;

        List<Teacher> user = teacherMapper.getAccount(worknum,password);
        if(!user.isEmpty()){
            result = "TEACHER";
        } else{
            if(worknum == "admin" && password == "123456") {
                result = "ADMIN";
            } else {
                result = "ERROR";
            }
        }
        return result;
    }
}
