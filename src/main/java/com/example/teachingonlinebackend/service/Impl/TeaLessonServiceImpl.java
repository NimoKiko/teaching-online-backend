package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.TeaLesson;
import com.example.teachingonlinebackend.mapper.TeaLessonMapper;
import com.example.teachingonlinebackend.service.TeaLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeaLessonServiceImpl extends ServiceImpl<TeaLessonMapper, TeaLesson> implements TeaLessonService {

    @Autowired
    private TeaLessonMapper teaLessonMapper;

    @Override
    public boolean saveOrEdit(TeaLesson teaLesson) {
        String teaname = teaLesson.getTeaname();
        String lessonName = teaLesson.getLessonName();
        String worknum = teaLessonMapper.getWorknumByName(teaname,lessonName);
        Integer lessonId = teaLessonMapper.getLessonIdByName(teaname,lessonName);
        teaLesson.setWorknum(worknum);
        teaLesson.setLessonId(lessonId);
//        return false;
        return saveOrUpdate(teaLesson);
    }

    @Override
    public boolean updateTea(TeaLesson teaLesson) {
        String teaname = teaLesson.getTeaname();
        String lessonName = teaLesson.getLessonName();
        //通过新的教师名字和课程找到新的教师工号
        String worknum = teaLessonMapper.getWorknumByName(teaname,lessonName);
        //课程id会在调接口的时候传给我
        Integer lessonId = teaLesson.getLessonId();

        teaLesson.setWorknum(worknum);
        teaLesson.setLessonId(lessonId);
        //根据lesson_id查要改的那条数据
        QueryWrapper<TeaLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("lesson_id",lessonId);

        return update(teaLesson,queryWrapper);
    }
}
