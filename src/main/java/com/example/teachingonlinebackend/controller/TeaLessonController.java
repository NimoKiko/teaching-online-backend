package com.example.teachingonlinebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teachingonlinebackend.entity.TeaLesson;
import com.example.teachingonlinebackend.service.Impl.TeaLessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teaLesson")
public class TeaLessonController {

    //注入service层
    @Autowired
    private TeaLessonServiceImpl teaLessonService;

    /*
    * 添加课程时将教师课程表存入数据库
    * url:/teaLesson/save
    * 请求方式：post
    *
    * */
    @PostMapping("/save")
    public boolean saveOrEdit(@RequestBody TeaLesson teaLesson){
//        return false;
        return teaLessonService.saveOrEdit(teaLesson);
    }

    /*
    *修改因为课程信息改变而改变的教师课程表（根据课程id进行更新）
    * url:/teaLesson/update
    * 请求方式：post
    * */
    @PostMapping("/update")
    public boolean updateTeaLesson(@RequestBody TeaLesson teaLesson){
        return teaLessonService.updateTea(teaLesson);
    }

    /*
    * 根据worknum获取教师课程表信息
    * url:/teaLesson/getList
    * 请求方式：get
    *
    * */
    @GetMapping("/getList")
    public List<TeaLesson> getTeaLessonList(@RequestParam Integer worknum){

        QueryWrapper<TeaLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("worknum",worknum);

         return teaLessonService.list(queryWrapper);
    }
}
