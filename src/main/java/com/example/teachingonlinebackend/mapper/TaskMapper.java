package com.example.teachingonlinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teachingonlinebackend.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
