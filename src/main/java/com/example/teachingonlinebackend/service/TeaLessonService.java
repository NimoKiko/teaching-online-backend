package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teachingonlinebackend.entity.TeaLesson;

public interface TeaLessonService extends IService<TeaLesson> {

    boolean saveOrEdit(TeaLesson teaLesson);

    boolean updateTea(TeaLesson teaLesson);
}
