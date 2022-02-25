package com.example.teachingonlinebackend.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Message;
import com.example.teachingonlinebackend.mapper.MessageMapper;
import com.example.teachingonlinebackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean saveMsg(Message message) {
        String worknum = message.getWorknum();
        String teaname = messageMapper.getTeaname(worknum);
        message.setTeaname(teaname);
        Integer lessonId = message.getLessonId();
        String lessonName = messageMapper.getLessonName(lessonId);
        message.setLessonName(lessonName);
        System.out.println(message);
        return save(message);
    }

    @Override
    public List<Message> getMessageByWorknum(String worknum) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("worknum",worknum);

        return list(queryWrapper);
    }

    @Override
    public List getMessageByStdnum(String stdnum) {
        List<Message> lessonIdList = messageMapper.getLessonIdList(stdnum);
        List msgList = new ArrayList();
        for(Message item : lessonIdList){
            Integer lessonId = item.getLessonId() * 1;
            List<Message> row = messageMapper.getMesByLessonId(lessonId);
            for(Message val : row){
               JSONObject obj =  dataOperation(val);
               if(!obj.isEmpty()){
                   msgList.add(obj);
               }
            }
        }
        System.out.println(msgList);
        return msgList;
    }
    public JSONObject dataOperation(Message item){
        JSONObject obj = new JSONObject();
        obj.put("id",item.getId());
        obj.put("worknum",item.getWorknum());
        obj.put("teaname",item.getTeaname());
        obj.put("title",item.getTitle());
        obj.put("content",item.getContent());
        obj.put("nodeId",item.getNodeId());
        obj.put("lessonId",item.getLessonId());
        obj.put("lessonName",item.getLessonName());
        obj.put("type",item.getType());
        obj.put("createTime",item.getCreateTime());

        return obj;
    }
}
