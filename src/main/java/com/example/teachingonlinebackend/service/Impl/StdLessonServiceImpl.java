package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.mapper.StdLessonMapper;
import com.example.teachingonlinebackend.service.StdLessonService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StdLessonServiceImpl extends ServiceImpl<StdLessonMapper, StdLesson> implements StdLessonService {

    @Autowired
    private StdLessonMapper stdLessonMapper;


    @Override
    public boolean saveInvite(String stdnum,String inviteCode) {

        System.out.println(inviteCode);
        System.out.println(stdnum);
        StdLesson stdLesson = new StdLesson();

        Integer lessonId = stdLessonMapper.getLessonId(inviteCode);
        String lessonName = stdLessonMapper.getLessonName(inviteCode);
        String teaname = stdLessonMapper.getTeaname(inviteCode);
        String stdname = stdLessonMapper.getStdname(stdnum);

        stdLesson.setStdnum(stdnum);
        stdLesson.setLessonId(lessonId);
        stdLesson.setLessonName(lessonName);
        stdLesson.setTeaname(teaname);
        stdLesson.setStdname(stdname);

        System.out.println(stdLesson);
        boolean isSuccess = save(stdLesson);

        //根据邀请码能查到是哪门课，查询这门课下的学生人数，存到lesson表的total字段中
        Integer total = stdLessonMapper.getTotal(lessonId);
        System.out.println(lessonId);
        boolean success = stdLessonMapper.saveTotal(total,lessonId);
        System.out.println(success);

        return isSuccess;
    }

    @Override
    public boolean judgeScore(Integer correctCount, String stdnum, Integer nodeId) {

        Integer allTask = stdLessonMapper.getAllTask(nodeId);
        //计算每道题的均分
        double perScore = 100d / allTask;
        System.out.println(perScore);
        //计算该学生这个任务节点的总分
        double score = perScore * correctCount;
        //将分数根据学生id存到表里
        boolean isUpdate = stdLessonMapper.saveScore((int)score,stdnum,nodeId);
        //修改task_isfinished表中该学生对应任务节点的完成情况
        boolean updateSituation = stdLessonMapper.updateSituation(stdnum,nodeId);

        return true;
    }
}
