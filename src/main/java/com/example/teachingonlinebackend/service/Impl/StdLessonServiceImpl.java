package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.mapper.StdLessonMapper;
import com.example.teachingonlinebackend.service.StdLessonService;
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
}
