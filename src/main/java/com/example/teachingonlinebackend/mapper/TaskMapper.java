package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.entity.Task;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    //通过nodeId查询对应的lessonId
    @Select("select lesson_id from tree where node_id = #{nodeId}")
    Integer getLessonId(Integer nodeId);

    //通过lessonId查询这门课程下有哪些学生，返回一个stdnum列表
    @Select("select stdnum from std_lesson where lesson_id = #{lessonId}")
    List<StdLesson> getStdnumByLessonId(Integer lessonId);

    //通过stdnum和nodeId查询表中是否已经存在该学生的任务记录
    @Select("select count(*) from task_isfinished where stdnum = #{stdnum} and node_id = #{nodeId}")
    Integer searchTaskIsFinished(String stdnum,Integer nodeId);

    //将学生与新增的课程作业之间的联系存入task_isfinished表
    @Insert("insert into task_isfinished(stdnum,node_id) values(#{stdnum},#{nodeId})")
    boolean saveStdTaskIsFinished(String stdnum,Integer nodeId);

    //通过stdnum和nodeId查询该学生指定任务节点完成情况
    @Select("select is_finished from task_isfinished where stdnum = #{stdnum} and node_id = #{nodeId}")
    Integer getTaskSituation(String stdnum, Integer nodeId);

    //通过stdnum和nodeId查询学生成绩
    @Select("select score from task_isfinished where stdnum = #{stdnum} and node_id = #{nodeId}")
    Integer getScoreByNum(String stdnum, Integer nodeId);
}
