package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teachingonlinebackend.entity.Trees;

public interface TreeService extends IService<Trees> {
    boolean saveNode(Trees tree);
}
