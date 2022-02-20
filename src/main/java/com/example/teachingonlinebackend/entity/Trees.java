package com.example.teachingonlinebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tree")
public class Trees {

    //主键，节点id
    @TableId(value = "node_id",type = IdType.AUTO)
    private Integer nodeId;
    //节点内容
    @TableField("node")
    private String node;
    //课程id
    @TableField("lesson_id")
    private Integer lessonId;
    //课程名称
    @TableField("lesson_name")
    private  String lessonName;
    //父节点
    @TableField("parent_id")
    private Integer parentId;

}
