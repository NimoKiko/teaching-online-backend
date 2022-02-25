package com.example.teachingonlinebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teachingonlinebackend.entity.Message;
import com.example.teachingonlinebackend.mapper.MessageMapper;

import java.util.List;

public interface MessageService extends IService<Message> {

    //保存消息
    boolean saveMsg(Message message);
    //通过教师工号查询发布的通知
    List<Message> getMessageByWorknum(String worknum);
    //通过学生学号查询该学生参与的课程下发布的全部通知
    List getMessageByStdnum(String stdnum);
}
