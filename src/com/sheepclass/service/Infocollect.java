package com.sheepclass.service;

import com.sheepclass.dao.MistakesDao;
import com.sheepclass.dao.ScheduleDao;
import com.sheepclass.dao.implement.MistakesDaoImpl;
import com.sheepclass.dao.implement.ScheduleDaoimpl;
import com.sheepclass.entity.Mistakes;
import com.sheepclass.entity.Schedule;

public class Infocollect {
    MistakesDao mistakesDao = null;
    ScheduleDao scheduleDao = null;
    int setSchedule(Schedule schedule){//断点
        if (scheduleDao == null)
            scheduleDao = new ScheduleDaoimpl();
        if (scheduleDao.getScheduleByuserid(schedule.getUserid()) == null)
            return scheduleDao.addSchedule(schedule);
        else
            return scheduleDao.updateScheduleByuserid(schedule);
    }

    int addMistakes(Mistakes mistakes){
        if (mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        return mistakesDao.addMistakes(mistakes);
    }

    int setMistakes(Mistakes mistakes){
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
}
