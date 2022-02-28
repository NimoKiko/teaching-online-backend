package com.example.teachingonlinebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teachingonlinebackend.entity.Task;
import com.example.teachingonlinebackend.service.Impl.TaskServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    //注入service层
    @Autowired
    private TaskServiceImpl taskService;

    /*
    * 根据nodeId查询作业表
    * url:/task/getTask
    * 请求方式：get
    *
    * */
    @GetMapping("/getTask")
    public List<Task> getTask(@RequestParam Integer nodeId){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("node_id",nodeId);
        return taskService.list(queryWrapper);
    }

    /*
    * 根据node_id保存作业
    * url:/task/saveTask
    * 请求方式：post
    *
    * */
    @PostMapping("/saveTask")
    public boolean save(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    /*
    * 根据taskId删除作业
    * url:/task/delTask
    * 请求方式：delete
    *
    * */
    @DeleteMapping("/delTask")
    public boolean delTask(@RequestParam Integer taskId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("*").eq("task_id",taskId);

        return taskService.remove(queryWrapper);
    }

    /*
    * 根据stdnum和nodeId获取该学生此任务节点完成情况
    * url:/task/getTaskSituation
    * 请求方式：get
    *
    * */
    @GetMapping("/getTaskSituation")
    public Integer getTaskSituation(@RequestParam String stdnum,
                                    @RequestParam Integer nodeId){
        return taskService.getTaskSituation(stdnum,nodeId);
    }

}
