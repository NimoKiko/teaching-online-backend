package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teachingonlinebackend.entity.StdLesson;

public interface StdLessonService extends IService<StdLesson> {
    boolean saveInvite(String stdnum,String inviteCode);

    boolean judgeScore(Integer correctCount,String stdnum,Integer nodeId);

    boolean studentSignIn(Integer lessonId, String stdnum);

}
