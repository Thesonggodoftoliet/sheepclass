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
        if (scheduleDao.getScheduleBycourseid(schedule.getUserid(),schedule.getCourseid()) == null)//根据用户和课程查找
            return scheduleDao.addSchedule(schedule);
        else
            return scheduleDao.updateScheduleBycourseid(schedule);
    }

    public int addMistakes(Mistakes mistakes){//根据已有题目创建错题
        if (mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        if (mistakesDao.getMistakesById(mistakes) == null && mistakes.getWrongtimes() == 1)//如果数据库中没有这道题并且这道题错了
            return mistakesDao.addMistakes(mistakes);
        else if (mistakesDao.getMistakesById(mistakes)!=null)
            return setMistakes(mistakes);
        else
            return -1;//数据库中没有这道错题并且做对了
    }

    public int addMistakes(int courseid,String content,int userid){//根据图片识别知识点，创建错题
        if(mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        Knowledge knowledge = SearchUtils.getKnowledge(content);
        if (knowledge == null)
            return -1;//没有找到符合的知识点
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

        if (sqlmistakes.getReviewtimes()-sqlmistakes.getWrongtimes()>=3) {//当做对的次数减去做错的次数大于等于3
            mistakesDao.deleteMistakesByhomeworkid(mistakes.getUserid(),mistakes.getHomeworkid());
            return -1;//删除错题
        }else
            return mistakesDao.updateMistakes(mistakes);
    }

    public int updateUsers(Users users){
        if (userDao == null)
            userDao =new UserDaoIm();
        Users sqluser = userDao.getUserById(users.getUserid());
        if (users.getIdentity()!=0&&users.getIdentity() != sqluser.getIdentity())
            sqluser.setIdentity(users.getIdentity());
        if (users.getTot_time()!=0&&users.getTot_time() != sqluser.getTot_time())
            sqluser.setTot_time(users.getTot_time());
        if (!users.getEmail().isEmpty()&&!users.getEmail().equals(sqluser.getEmail()))
            sqluser.setEmail(users.getEmail());
        if (!users.getPhone().isEmpty()&&!users.getPhone().equals(sqluser.getPhone()))
            sqluser.setPhone(users.getPhone());
        if (!users.getNickname().isEmpty()&&!users.getNickname().equals(sqluser.getNickname()))
            sqluser.setNickname(users.getNickname());
        if (users.getBirthday()!=0&&users.getBirthday()!=sqluser.getBirthday())
            sqluser.setBirthday(users.getBirthday());
        if (users.getSex()!=0&&users.getSex()!= sqluser.getSex())
            sqluser.setSex(users.getSex());
        return userDao.setUser(sqluser);
    }

    public int createView(int userid){
        if (mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        return mistakesDao.createViewByuserid(userid);
    }

    public int caculateTime(Users users){
        if (userDao == null)
            userDao = new UserDaoIm();
        Users sqluser = userDao.getUserById(users.getUserid());
        long time = sqluser.getTot_time()+users.getTot_time();
        sqluser.setTot_time(time);
        return userDao.updateTotTime(users);
    }
}
