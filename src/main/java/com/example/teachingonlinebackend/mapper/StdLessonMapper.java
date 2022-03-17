package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.StdLesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StdLessonMapper extends BaseMapper<StdLesson> {

    //根据邀请码获取课程id
    @Select("select lesson_id from lesson where invite_code = #{inviteCode}")
    Integer getLessonId(String inviteCode);

    //根据邀请码获取课程名称
    @Select("select lesson_name from lesson where invite_code = #{inviteCode}")
    String getLessonName(String inviteCode);

    //根据邀请码获取教师姓名
    @Select("select teaname from lesson where invite_code = #{inviteCode}")
    String getTeaname(String inviteCode);

    //根据学生学号获取学生姓名
    @Select("select stdname from student where stdnum = #{stdnum}")
    String getStdname(String stdnum);

    //根据课程id查询由多少学生选了这门课
    @Select("select count(*) from std_lesson where lesson_id = #{lessonId}")
    Integer getTotal(Integer lessonId);

    //根据课程id更新课程中选了这门课的学生总人数
    @Update("update lesson set total_std = #{total} where lesson_id = #{lessonId}")
    boolean saveTotal(Integer total,Integer lessonId);

    //根据nodeId查询全部作业数
    @Select("select count(*) from task where node_id = #{nodeId}")
    Integer getAllTask(Integer nodeId);

    //根据stdnum和lessonId将成绩存进表里
    @Update("update task_isfinished set score = #{score} where stdnum = #{stdnum} and node_id = #{nodeId}")
    boolean saveScore(Integer score,String stdnum,Integer nodeId);

    //根据stdnum和nodeId对task_isfinished表中的is_finished字段进行更新
    @Update("update task_isfinished set is_finished = 1 where stdnum = #{stdnum} and node_id = #{nodeId}")
    boolean updateSituation(String stdnum,Integer nodeId);

    //根据stdnum和lessonId找到对应的数据使打卡次数加一
    @Update("update std_lesson set sign_times = sign_times+1 where stdnum = #{stdnum} and lesson_id = #{lessonId}")
    boolean stdSignTimesAdd(Integer lessonId, String stdnum);
}
