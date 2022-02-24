package com.example.teachingonlinebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.teachingonlinebackend.entity.Student;
import com.example.teachingonlinebackend.service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/std")
public class StudentController {


    //注入service层
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /*
    * 查询所有数据接口
    *   /std/queryAll
    */
    @GetMapping("/queryAll")
    public List<Student> query() {
        return studentServiceImpl.list();
    }
    /*
    * 新增/更新学生接口
    *  /std/addOrUpdate
    *  post请求
    * */
    @PostMapping("/addOrUpdate")
    public boolean save(@RequestBody Student student) {
        return studentServiceImpl.saveStd(student);
    }
    /*
    * 删除学生接口
    *   /std/deleteStd
    * DELETE请求
    * 根据学号删除学生
    * */
    @DeleteMapping("/deleteStd")
    public boolean delete(@RequestParam Integer id) {
        return studentServiceImpl.removeById(id);
    }
    /*
    * 分页查询接口
    *@RequestParam注解接收 ?pageNum=1&pageSize=10 这样的参数
    *
     */
    @PostMapping("/page")
    public IPage<Student> queryPage(@RequestBody Map<String,Object> params) {
        System.out.println(params);
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        String stdname = (String) params.get("stdname");
        String stdnum = (String) params.get("stdnum");
        String dept = (String) params.get("dept");
        String sex = (String) params.get("sex");

        IPage<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("stdname",stdname);
        queryWrapper.orderByDesc("create_time");
        if(!"".equals(stdname)) {
            queryWrapper.like("stdname",stdname);
        }
        if(!"".equals(stdnum)) {
            queryWrapper.like("stdnum",stdnum);
        }
        if(!"".equals(dept)) {
            queryWrapper.like("dept",dept);
        }
        IPage<Student> studentPage =  studentServiceImpl.page(page, queryWrapper);
        return studentPage;
    }

    /*
    * 修改学生性别的接口
    * url:/std/updateSex
    * 请求方式：get
    *
    * */
    @GetMapping("/updateSex")
    public boolean stdUpdateSex(@RequestParam String stdnum, @RequestParam String sex){

        return studentServiceImpl.updateSex(stdnum,sex);
    }



}
