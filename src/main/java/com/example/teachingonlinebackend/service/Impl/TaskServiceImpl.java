package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.entity.Task;
import com.example.teachingonlinebackend.mapper.TaskMapper;
import com.example.teachingonlinebackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    //保存或修改作业表
    public boolean saveTask(Task task){

        Integer nodeId = task.getNodeId();
        //通过nodeId在tree表中查询对应的lessonId
        Integer lessonId = taskMapper.getLessonId(nodeId);
        //通过lessonId找到此门课程下有什么学生并返回stdnum
        List<StdLesson> stdnumList = taskMapper.getStdnumByLessonId(lessonId);
        for(StdLesson item : stdnumList){
            String stdnum = item.getStdnum();
            //先判断表task_isfinished中是否以及存在了该学生的任务记录
            Integer record = taskMapper.searchTaskIsFinished(stdnum,nodeId);
            if(record == 0) {
                //建立单个章节的任务和单个学生之间的联系
                boolean isSaved = taskMapper.saveStdTaskIsFinished(item.getStdnum(),nodeId);
                System.out.println(isSaved);
            } else {
                System.out.println("该学生的任务已经创建不用重复创建");
            }
        }

        return saveOrUpdate(task);
    }


    @Override
    public Integer getTaskSituation(String stdnum, Integer nodeId) {
        Integer situation = taskMapper.getTaskSituation(stdnum,nodeId);
        System.out.println(situation);
        if(situation != null) {
            if(situation == 1){
                //根据学号和nodeId查分数
                Integer score = taskMapper.getScoreByNum(stdnum,nodeId);
                return score;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
