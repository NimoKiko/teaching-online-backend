package com.example.teachingonlinebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.teachingonlinebackend.entity.Student;
import com.example.teachingonlinebackend.mapper.StudentMapper;
import com.example.teachingonlinebackend.service.StudentService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/std")
public class StudentController {


    //注入service层
    @Autowired
    private StudentService studentService;

    /*
    * 查询所有数据接口
    *   /std/queryAll
    */
    @GetMapping("/queryAll")
    public List<Student> query() {
//        return studentMapper.queryAll();
        return studentService.list();
    }


    /*
    * 新增/更新学生接口
    *  /std/addOrUpdate
    *  post请求
    * */
    @PostMapping("/addOrUpdate")
    public boolean save(@RequestBody Student student) {
        return studentService.saveStd(student);
    }

    /*
    * 删除学生接口
    *   /std/deleteStd
    * DELETE请求
    * */
    @DeleteMapping("/deleteStd/{id}")
    public boolean delete(@PathVariable Integer id) {
//        return studentMapper.deleteById(id);
        return studentService.removeById(id);  //mybatis-plus自带的方法
    }

    /*
    * 分页查询接口
    *@RequestParam注解接收 ?pageNum=1&pageSize=10 这样的参数
    *
     */
    @GetMapping("/page")
    public IPage<Student> queryPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String stdname) {
//        pageNum = (pageNum - 1) * pageSize;
//        Integer total = studentMapper.queryTotal();
//        List<Student> data = studentMapper.queryPage(pageNum, pageSize);
//        Map<String ,Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
        IPage<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("stdname",stdname);
        IPage<Student> studentPage =  studentService.page(page, queryWrapper);
        return studentPage;
    }
}
