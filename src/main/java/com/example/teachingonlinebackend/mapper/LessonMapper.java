package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

    /*
    * 通过teaname在teacher表中查询worknum
    * */
    @Select("select worknum from teacher where teaname = #{teaname}")
    String getWorknum(String teaname);
}
