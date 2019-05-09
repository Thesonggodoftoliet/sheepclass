package com.sheepclass.service;

import com.sheepclass.dao.HomeworkDao;
import com.sheepclass.dao.MistakesDao;
import com.sheepclass.dao.ScheduleDao;
import com.sheepclass.dao.UserDao;
import com.sheepclass.dao.implement.HomeworkDaoImpl;
import com.sheepclass.dao.implement.MistakesDaoImpl;
import com.sheepclass.dao.implement.ScheduleDaoimpl;
import com.sheepclass.dao.implement.UserDaoIm;
import com.sheepclass.entity.*;
import com.sheepclass.utils.SearchUtils;

import java.util.List;

public class Infocollect {
    MistakesDao mistakesDao = null;
    ScheduleDao scheduleDao = null;
    HomeworkDao homeworkDao = null;
    UserDao userDao = null;
    public int setSchedule(Schedule schedule){//断点
        if (scheduleDao == null)
            scheduleDao = new ScheduleDaoimpl();
        if (scheduleDao.getScheduleByuserid(schedule.getUserid()) == null)
            return scheduleDao.addSchedule(schedule);
        else
            return scheduleDao.updateScheduleBycourseid(schedule);
    }

    public int addMistakes(Mistakes mistakes){
        if (mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        return mistakesDao.addMistakes(mistakes);
    }

    public int addMistakes(int courseid,String content,int userid){
        if(mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        Knowledge knowledge = SearchUtils.getKnowledge(content);
        Learn learn = new Learn();
        List<Homework> homeworks =learn.getHomeworkByKnow(knowledge.getKnowledgeid());
        int tag = 0;
        for (int i=0;i<homeworks.size();i++){
            Mistakes mistakes = new Mistakes();
            mistakes.setWrongtimes(1);
            mistakes.setReviewtimes(0);
            mistakes.setCourseid(courseid);
            mistakes.setHomeworkid(homeworks.get(i).getHomeworkid());
            mistakes.setUserid(userid);
            tag=mistakesDao.addMistakes(mistakes);
            if (i>=4)//加入最多五道练习题
                break;
        }
        return tag;
    }

    public int setMistakes(Mistakes mistakes){
        if (mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        Mistakes sqlmistakes = mistakesDao.getMistakesById(mistakes);//前端正确就设review为1 否则设wrong为1
        if (mistakes.getReviewtimes() == 1)
            sqlmistakes.setReviewtimes(sqlmistakes.getReviewtimes()+1);
        if (mistakes.getWrongtimes() == 1)
            sqlmistakes.setWrongtimes(sqlmistakes.getWrongtimes()+1);

        if (sqlmistakes.getReviewtimes() >=3) {
            mistakesDao.deleteMistakesByhomeworkid(mistakes.getUserid(),mistakes.getHomeworkid());
            return -1;//删除错题
        }else
            return mistakesDao.updateMistakes(mistakes);
    }

    public int updateUsers(Users users){
        if (userDao == null)
            userDao =new UserDaoIm();
        return userDao.setUser(users);
    }
}
