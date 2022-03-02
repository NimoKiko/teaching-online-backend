package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.entity.Student;
import com.example.teachingonlinebackend.entity.Teacher;
import com.example.teachingonlinebackend.entity.Trees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    //根据工号和密码查询教师账号
    @Select("select * from teacher where worknum = #{worknum} and password = #{password}")
    List<Teacher> getAccount(String worknum,String password);

    //更具学号和密码查询学生账号
    @Select("select * from student where stdnum = #{stdnum} and password = #{password}")
    List<Student> getStudentAccount(String stdnum,String password);

    //根据lessonId获取该课程下全部的任务节点
    @Select("select * from tree where lesson_id = #{lessonId}")
    List<Trees> getAllTaskNode(Integer lessonId);

    //根据lessonId查询该门课程下的全部学生
    @Select("select * from std_lesson where lesson_id = #{lessonId}")
    List<StdLesson> getStudentList(Integer lessonId);

    //根据stdnum和nodeId查询学生成绩
    @Select("select score from task_isfinished where stdnum = #{stdnum} and node_id = #{nodeId}")
    Integer getScore(String stdnum,Integer nodeId);

    //根据stdnum和lessonId将成绩存入std_lesson表
    @Update("update std_lesson set score = #{finalScore} where stdnum = #{stdnum} and lesson_id = #{lessonId}")
    boolean saveFinalScore(String stdnum,Integer lessonId,Integer finalScore);

    //根据lessonId将对应课程的课程状态设置为已完成（0表示未完成，1表示已完成）
    @Update("update lesson set is_end = 1 where lesson_id = #{lessonId}")
    boolean setLessonStatus(Integer lessonId);
}
