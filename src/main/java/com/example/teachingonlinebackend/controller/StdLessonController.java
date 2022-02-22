package com.example.teachingonlinebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teachingonlinebackend.entity.StdLesson;
import com.example.teachingonlinebackend.service.Impl.StdLessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stdLesson")
public class StdLessonController {

    @Autowired
    private StdLessonServiceImpl stdLessonService;

    /*
    * 获取课程表中学生的列表接口
    * url:/stdLesson/getStudent
    * 请求方式：get
    *
    * */
    @GetMapping("/getStudent")
    public List<StdLesson> getList(@RequestParam Integer id) {
        QueryWrapper<StdLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("lesson_id",id);
        return stdLessonService.list(queryWrapper);
    }

    /*
    *根据stdnum获取对应的学生参与的课程
    * url:/stdLesson/getStudentByNum
    * 请求方式：get
    *
    * */
    @GetMapping("/getStudentByNum")
    public List<StdLesson> getListByNum(@RequestParam Integer id){
        QueryWrapper<StdLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("stdnum",id);

        return stdLessonService.list(queryWrapper);
    }

}
