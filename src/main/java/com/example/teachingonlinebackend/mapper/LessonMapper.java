package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

    /*
    * 通过teaname在teacher表中查询worknum
    * */
    @Select("select worknum from teacher where teaname = #{teaname}")
    String getWorknum(String teaname);

    //根据lessonId获取当前上课情况
    @Select("select on_class from lesson where lesson_id = #{lessonId}")
    Integer getCurrentStatus(Integer lessonId);

    //根据lessonId更新课程状态
    @Update("update lesson set on_class = #{status} where lesson_id = #{lessonId}")
    boolean changeStatus(Integer lessonId,Integer status);
}
