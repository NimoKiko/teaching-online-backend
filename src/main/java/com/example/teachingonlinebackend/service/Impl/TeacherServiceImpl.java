package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Teacher;
import com.example.teachingonlinebackend.mapper.TeacherMapper;
import com.example.teachingonlinebackend.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    //添加或修改教师信息
    public boolean saveTea(Teacher teacher) {
        return saveOrUpdate(teacher);
    }
}
