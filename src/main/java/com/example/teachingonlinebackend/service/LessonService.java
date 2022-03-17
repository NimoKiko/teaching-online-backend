package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teachingonlinebackend.entity.Lesson;

public interface LessonService extends IService<Lesson> {

    boolean changeClassStatus(Integer lessonId);

    Integer getCurrentStatus(Integer lessonId);
}
