package com.example.teachingonlinebackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.teachingonlinebackend.entity.Teacher;
import com.example.teachingonlinebackend.service.Impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tea")
public class TeacherController {

    //注入service层
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    /*
     * 增加或修改教师信息的接口
     * url:/tea/addOrUpdate
     * 请求方式：post
     *
     * */
    @PostMapping("/addOrUpdate")
    public boolean saveOrUpdateTeacher(@RequestBody Teacher teacher) {
        return teacherServiceImpl.saveTea(teacher);
    }

    /*
     * 删除教师信息的接口
     * url:/tea/delTeacher
     * 请求方式：delete
     *
     * */
    @DeleteMapping("/delTeacher")
    public boolean delTeacher(@RequestParam Integer id) {
        return teacherServiceImpl.removeById(id);
    }

    /*
     * 分页查询教师信息(模糊查询)
     * url:/tea/queryPage
     * 请求方式：post
     *
     * */
    @PostMapping("/queryPage")
    public IPage<Teacher> queryPage(@RequestBody JSONObject params) {
//        System.out.println(params);
        int pageNum = params.getInteger("pageNum");
        int pageSize = params.getInteger("pageSize");
        String worknum = params.getString("worknum");
        String teaname = params.getString("teaname");
        String dept = params.getString("dept");
        String type = params.getString("type");

        IPage<Teacher> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (!"".equals(worknum)) {
            queryWrapper.like("worknum", worknum);
        }
        if (!"".equals(teaname)) {
            queryWrapper.like("teaname", teaname);
        }
        if (!"".equals(dept)) {
            queryWrapper.like("dept", dept);
        }
        if (!"".equals(type)) {
            queryWrapper.like("type", type);
        }
        IPage<Teacher> teacherIPage = teacherServiceImpl.page(page,queryWrapper);
        return teacherIPage;
    }
}
