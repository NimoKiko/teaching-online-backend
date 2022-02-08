package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Student;
import com.example.teachingonlinebackend.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

//    @Autowired
//    private StudentMapper studentMapper;

//    public Integer save(Student student){
//        if(student.getId() == null) {               //找不到id就插入
//            return studentMapper.insert(student);
//        } else {                                    //找到id就修改
//            return studentMapper.update(student);
//        }
//    }
    public boolean saveStd(Student student){
//        if(student.getId() == null){
//            return save(student);  //mybatis-plus提供的插入数据的方法
//        } else {
//            return updateById(student);
//        }

        return saveOrUpdate(student);

    }
}
