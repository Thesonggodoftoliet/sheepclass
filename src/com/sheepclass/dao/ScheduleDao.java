package com.sheepclass.dao;

import com.sheepclass.entity.Schedule;

public interface ScheduleDao {
    Schedule getScheduleByuserid(int userid);
    Schedule getScheduleBycourseid(int userid,int courseid);
    int addSchedule(Schedule schedule);
    int updateScheduleBycourseid(Schedule schedule);
    int deleteScheduleByuserid(int userid);
}
