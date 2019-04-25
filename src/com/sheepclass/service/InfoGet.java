package com.sheepclass.service;

import com.sheepclass.dao.LearninginfoDao;
import com.sheepclass.dao.implement.LearninginfoDaoimple;

import java.util.Calendar;

public class InfoGet {
    //学情管理，包含预警
    //七天一个周期
    int getWeeklogintimes(int userid){
        Calendar calendar = Calendar.getInstance();
        calendar.getFirstDayOfWeek();
        LearninginfoDao learninginfoDao = new LearninginfoDaoimple();
        calendar.add(Calendar.DATE,-calendar.getTime().getDay());
        return learninginfoDao.getTimesoflearning(userid,calendar.getTimeInMillis());
    }

    
}
