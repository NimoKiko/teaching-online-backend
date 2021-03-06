package com.example.teachingonlinebackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.teachingonlinebackend.entity.Lesson;
import com.example.teachingonlinebackend.service.Impl.LessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*
    * 获取课程是否已完结状态接口
    * url:/lesson/isEnd
    * 请求方式：get
    *
    * */
    @GetMapping("/isEnd")
    public Integer getLessonStatus(@RequestParam Integer lessonId){
        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("is_end").eq("lesson_id",lessonId);
        Lesson target = lessonServiceImpl.getOne(queryWrapper);
        return target.getIsEnd();
    }

    /*
    * 获取当前课程状态
    * url:/lesson/currentStatus
    * 请求方式：get
    *
    * */
    @GetMapping("/currentStatus")
    public Integer getCurrentStatus(@RequestParam Integer lessonId){
        return lessonServiceImpl.getCurrentStatus(lessonId);
    }

    /*
    * 更改课程状态（正在上课/不在上课）
    * url:/lesson/isOnClass
    * 请求方式：get
    *
    * */
    @GetMapping("/isOnClass")
    public boolean changeClassStatus(@RequestParam Integer lessonId){

        return lessonServiceImpl.changeClassStatus(lessonId);
    }

}
