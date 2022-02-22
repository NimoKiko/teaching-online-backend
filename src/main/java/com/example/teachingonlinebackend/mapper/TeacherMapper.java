package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.Student;
import com.example.teachingonlinebackend.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select("select * from teacher where worknum = #{worknum} and password = #{password}")
    List<Teacher> getAccount(String worknum,String password);

    @Select("select * from student where stdnum = #{stdnum} and password = #{password}")
    List<Student> getStudentAccount(String stdnum,String password);
}
