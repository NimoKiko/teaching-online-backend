package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    //通过worknum获取教师名字
    @Select("select teaname from teacher where worknum = #{worknum}")
    String getTeaname(String workname);

    //通过lessonId获取lessonName
    @Select("select lesson_name from lesson where lesson_id = #{lessonId}")
    String getLessonName(Integer lessonId);

    //通过stdnum获取lessonId
    @Select("select lesson_id from std_lesson where stdnum = #{stdnum}")
    List<Message> getLessonIdList(String stdnum);

    //通过lessonId获取msg
    @Select("select * from message where lesson_id = #{lessonId}")
    List<Message> getMesByLessonId(Integer lessonLd);
}
