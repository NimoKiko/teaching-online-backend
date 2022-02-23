package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.StdLesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StdLessonMapper extends BaseMapper<StdLesson> {

    @Select("select lesson_id from lesson where invite_code = #{inviteCode}")
    Integer getLessonId(String inviteCode);

    @Select("select lesson_name from lesson where invite_code = #{inviteCode}")
    String getLessonName(String inviteCode);

    @Select("select teaname from lesson where invite_code = #{inviteCode}")
    String getTeaname(String inviteCode);

    @Select("select stdname from student where stdnum = #{stdnum}")
    String getStdname(String stdnum);

    @Select("select count(*) from std_lesson where lesson_id = #{lessonId}")
    Integer getTotal(Integer lessonId);

    @Update("update lesson set total_std = #{total} where lesson_id = #{lessonId}")
    boolean saveTotal(Integer total,Integer lessonId);
}
