package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Lesson;
import com.example.teachingonlinebackend.mapper.LessonMapper;
import com.example.teachingonlinebackend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    //新建课程
    public boolean saveLesson(Lesson lesson){
            String teaname = lesson.getTeaname();
            String worknum = lessonMapper.getWorknum(teaname);
            lesson.setWorknum(worknum);
        return saveOrUpdate(lesson);
    }
}
