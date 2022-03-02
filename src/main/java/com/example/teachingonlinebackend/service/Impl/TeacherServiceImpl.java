package com.example.teachingonlinebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teachingonlinebackend.controller.dto.UserDto;
import com.example.teachingonlinebackend.entity.*;
import com.example.teachingonlinebackend.mapper.TeacherMapper;
import com.example.teachingonlinebackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    //添加或修改教师信息
    public boolean saveTea(Teacher teacher) {
        return saveOrUpdate(teacher);
    }

    @Override
    public String login(UserDto userDto) {
        String worknum = userDto.getWorknum();
        String password = userDto.getPassword();

        String result = null;
        List<Teacher> user = teacherMapper.getAccount(worknum,password);
        if(!user.isEmpty()){
            result = "TEACHER";
        } else{
            if(worknum.equals("admin") && password.equals("123456")) {
                result = "ADMIN";
            } else {
                String stdnum = worknum;
                List<Student> stdUser = teacherMapper.getStudentAccount(stdnum,password);
                if(!stdUser.isEmpty()){
                    result = "STUDENT";
                } else{
                    result = "ERROR";
                }

            }
        }
        return result;
    }

    @Override
    public boolean updateSex(String worknum, String sex) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sex").eq("worknum",worknum);

        Teacher item = new Teacher();
        item.setSex(sex);

        return update(item,queryWrapper);
    }

    @Override
    public boolean endClass(Integer lessonId) {
        //根据lessonId查询该门课程下共有多少任务节点(非根节点)
        List<Trees> taskList = teacherMapper.getAllTaskNode(lessonId);
        List tempTaskList = new ArrayList();
        for(Trees item : taskList){
            if(!item.getParentId().equals(0)){      //排除根节点
                tempTaskList.add(item.getNodeId());
            }
        }
        //根据lessonId查询该课程下的所有学生
        List<StdLesson> stdList = teacherMapper.getStudentList(lessonId);
        //遍历学生列表
        for(StdLesson item : stdList){
            //创建按一个成绩列表，用于记录单个学生在这门课程中的每个任务节点的分数
            List scoreList = new ArrayList();
            //将单个学生的学号存在stdnum中
            String stdnum = item.getStdnum();
            //在task_isfinished表中寻找 该学生在这门课中，存在任务的节点的成绩
            for(int i = 0 ; i < tempTaskList.size() ; i++){
                Integer nodeId = (int)tempTaskList.get(i);
                //将成绩存入scoreList列表中
                scoreList.add(teacherMapper.getScore(stdnum,nodeId));
            }
            System.out.println("学号："+item.getStdnum()+",成绩："+scoreList);
            Integer total = 0;
            //遍历成绩列表，进行求和
            for(int i = 0 ; i < scoreList.size() ; i++){
                total = total + (int)scoreList.get(i);
            }
            //计算成绩的均分
            Integer finalScore = total / scoreList.size();
            System.out.println(finalScore);
            //将该学生的成绩录入std_lesson表中（通过stdnum和lessonId）
            teacherMapper.saveFinalScore(stdnum,lessonId,finalScore);
        }
        //根据lessonId将对应课程状态设置为已结束
        teacherMapper.setLessonStatus(lessonId);
        return true;
    }
}
