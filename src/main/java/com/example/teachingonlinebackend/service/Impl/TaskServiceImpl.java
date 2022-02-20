package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Task;
import com.example.teachingonlinebackend.mapper.TaskMapper;
import com.example.teachingonlinebackend.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    //保存或修改作业表
    public boolean saveTask(Task task){
        return saveOrUpdate(task);
    }
}
