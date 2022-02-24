package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Student;
import com.example.teachingonlinebackend.mapper.StudentMapper;
import com.example.teachingonlinebackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    public boolean saveStd(Student student){
        return saveOrUpdate(student);
    }

    @Override
    public boolean updateSex(String stdnum, String sex) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("sex").eq("stdnum",stdnum);

        Student item = new Student();
        item.setSex(sex);

        return update(item,queryWrapper);
    }
}
