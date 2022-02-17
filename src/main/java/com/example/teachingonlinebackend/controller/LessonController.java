package com.example.teachingonlinebackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.teachingonlinebackend.entity.Lesson;
import com.example.teachingonlinebackend.service.Impl.LessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    //注入service层
    @Autowired
    private LessonServiceImpl lessonServiceImpl;

    /*
    * 新增或修改课程表接口
    * url:/lesson/saveOrUpdate
    * 请求方式：post
    *
    * */
    @PostMapping("/saveOrUpdate")
    public boolean save(@RequestBody Lesson lesson){
        return lessonServiceImpl.saveLesson(lesson);
    }

    /*
    * 删除表中信息接口
    * url:/lesson/delLesson
    * 请求方式：delete
    *
    * */
    @DeleteMapping("/delLesson")
    public boolean delLesson(@RequestParam Integer id){
        return lessonServiceImpl.removeById(id);
    }

    /*
    * 分页查询
    * url:/lesson/queryPage
    * 请求方式：post
    *
    * */
    @PostMapping("/queryPage")
    public IPage<Lesson> queryPage(@RequestBody JSONObject params){
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        String lessonname = params.getString("lessonname");
        String teaname = params.getString("teaname");

        IPage<Lesson> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();

        if(!"".equals(lessonname)){
            queryWrapper.like("lesson_name",lessonname);
        }
        if(!"".equals(teaname)){
            queryWrapper.like("teaname",teaname);
        }
//        queryWrapper.orderByDesc("create_time");
        IPage<Lesson> lessonIPage = lessonServiceImpl.page(page,queryWrapper);
        return lessonIPage;
    }

}
