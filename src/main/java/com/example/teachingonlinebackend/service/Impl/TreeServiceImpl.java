package com.example.teachingonlinebackend.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Trees;
import com.example.teachingonlinebackend.mapper.TreesMapper;
import com.example.teachingonlinebackend.service.TreeService;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeServiceImpl extends ServiceImpl<TreesMapper, Trees> implements TreeService {

    //保存节点
    public boolean saveNode(Trees tree){
        return save(tree);
    }

    //获取树节点
    public List getTree(Integer lessonId){

        QueryWrapper<Trees> queryWrapper = new QueryWrapper<>();
        //通过课程id找到表中的全部数据
        queryWrapper.select("*").eq("lesson_id",lessonId);
        List<Trees> list = list(queryWrapper);


        List treeList = new ArrayList();

        //循环遍历这些数据
        for (Trees item : list){
//            System.out.println(item);
            Integer parentId = (Integer) item.getParentId();
            if(parentId == 0) {
                JSONObject tree =  dataOperation(item);
                treeList.add(tree);
            }
        }
        return treeList;
    }

    public JSONObject dataOperation(Trees item){

        JSONObject tree = new JSONObject();
        tree.put("lessonId",item.getLessonId());
        tree.put("lessonName",item.getLessonName());
        tree.put("nodeId",item.getNodeId());
        tree.put("node",item.getNode());
        tree.put("parentId",item.getParentId());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("*").eq("parent_id",item.getNodeId());

        List<Trees> childrenList = list(queryWrapper);
        List temp = new ArrayList();

        for(Trees val : childrenList){
            JSONObject tree2 = new JSONObject();
            tree2.put("lessonId",val.getLessonId());
            tree2.put("lessonName",val.getLessonName());
            tree2.put("nodeId",val.getNodeId());
            tree2.put("node",val.getNode());
            tree2.put("parentId",val.getParentId());

            temp.add(tree2);
        }
        if(!temp.isEmpty()){
            tree.put("childrenList",temp);
        }

        return tree;
    }
}
