package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teachingonlinebackend.controller.dto.UserDto;
import com.example.teachingonlinebackend.entity.Teacher;

public interface TeacherService extends IService<Teacher> {
    boolean saveTea(Teacher teacher);

    String login(UserDto userDto);
}
