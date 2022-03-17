package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.entity.Lesson;
import com.example.teachingonlinebackend.mapper.LessonMapper;
import com.example.teachingonlinebackend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    //新建课程
    public boolean saveLesson(Lesson lesson){
            String teaname = lesson.getTeaname();
            String worknum = lessonMapper.getWorknum(teaname);
            lesson.setWorknum(worknum);

            if(lesson.getInviteCode().equals("")){
                String inviteCode = getRandom();
                lesson.setInviteCode(inviteCode);
            }

        return saveOrUpdate(lesson);
    }

    //生成随机数
    public String getRandom(){
        int length = 8;
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                case 0:
                    result=Math.round(Math.random()*25+65);
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    //改变课程状态
    public boolean changeClassStatus(Integer lessonId){
        //根据lessonId获取当前状态

        Integer currentStatus = lessonMapper.getCurrentStatus(lessonId);

        if(currentStatus == 0 ){
            //表示下课状态
            return lessonMapper.changeStatus(lessonId,1);

        } else if(currentStatus == 1){
            //表示上课状态
            return lessonMapper.changeStatus(lessonId,0);
        }

        return false;

    }

    //获取当前课程状态
    public  Integer getCurrentStatus(Integer lessonId){
        return lessonMapper.getCurrentStatus(lessonId);
    }
}
