package com.example.teachingonlinebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teachingonlinebackend.entity.Message;
import com.example.teachingonlinebackend.service.Impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    /*
    * 保存消息接口
    * url:/msg/saveMessage
    * 请求方式：Post
    *
    * */
    @PostMapping("/saveMessage")
    public boolean saveMessage(@RequestBody Message message){
        return messageService.saveMsg(message);
    }

    /*
    * 通过教师工号查询该教师发布的通知
    * url:/msg/queryMessageByWorknum
    * 请求方式：get
    *
    * */
    @GetMapping("/queryMessageByWorknum")
    public List<Message> getMessageByWorknum(@RequestParam String worknum){

        return messageService.getMessageByWorknum(worknum);
    }

    /*
    * 通过学生学号查询该学生参与的课程发布的通知
    * url:/msg/queryMessageByStdnum
    * 请求方式：get
    *
    * */
    @GetMapping("/queryMessageByStdnum")
    public List getMessageByStdnum(@RequestParam String stdnum){

        return messageService.getMessageByStdnum(stdnum);
    }

    /*
    * 通过消息id查询消息
    * url:/msg/findMessageById
    *   请求方式：get
    *
    * */
    @GetMapping("/findMessageById")
    public List<Message> getMessageById(@RequestParam Integer id){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("id",id);
        return messageService.list(queryWrapper);
    }
}
