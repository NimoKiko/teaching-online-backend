package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.mapper.StdLessonMapper;
import com.example.teachingonlinebackend.service.StdLessonService;
import org.springframework.stereotype.Service;

@Service
public class StdLessonServiceImpl extends ServiceImpl<StdLessonMapper, StdLesson> implements StdLessonService {
}
