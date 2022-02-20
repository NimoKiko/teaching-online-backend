package com.example.teachingonlinebackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teachingonlinebackend.entity.Trees;
import com.example.teachingonlinebackend.service.Impl.TreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {

    //注入service层
    @Autowired
    private TreeServiceImpl treeService;

    /*
    * 保存节点
    * url:/tree/saveNode
    * 请求方式：post
    *
    * */
    @PostMapping("/saveNode")
    public boolean saveNode(@RequestBody Trees tree){
        return treeService.saveNode(tree);
    }

    /*
    * 获取结构树
    * url:/tree/getTree
    * 请求方式：get
    *
    * */
    @GetMapping("/getTree")
    public List getTree(@RequestParam Integer lessonId){
       return treeService.getTree(lessonId);
    }

    /*
    * 删除节点
    * url:/tree/delNode
    * 请求方式：delete
    *
    * */
    @DeleteMapping("/delNode")
    public boolean delNode(@RequestParam Integer nodeId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("*").eq("node_id",nodeId);
        return treeService.remove(queryWrapper);
    }

}
