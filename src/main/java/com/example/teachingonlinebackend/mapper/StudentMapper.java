package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

//    @Select("select * from student")
//    List<Student> queryAll();

//    @Insert("insert into student(stdname, stdnum, dept) values (#{stdname}, #{stdnum}, #{dept})")
//    Integer insert(Student student);

//    Integer update(Student student);

//    @Delete("delete from student where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);

//    @Select("select * from student limit #{pageNum}, #{pageSize}")
//    List<Student> queryPage(Integer pageNum, Integer pageSize);

//    @Select("select count(*) from student")
//    Integer queryTotal();
}
