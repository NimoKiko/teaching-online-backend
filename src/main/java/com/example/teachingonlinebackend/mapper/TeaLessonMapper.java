package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.TeaLesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeaLessonMapper extends BaseMapper<TeaLesson> {

    @Select("select worknum from lesson where teaname = #{teaname} and lesson_name = #{lessonName}")
    String getWorknumByName(String teaname,String lessonName);

    @Select("select lesson_id from lesson where teaname = #{teaname} and lesson_name = #{lessonName}")
    Integer getLessonIdByName(String teaname,String lessonName);
}
