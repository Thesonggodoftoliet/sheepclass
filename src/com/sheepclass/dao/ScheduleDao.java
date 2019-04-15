package com.sheepclass.dao;

import com.sheepclass.entity.Schedule;

public interface ScheduleDao {
    Schedule getScheduleByuserid(int userid);
    int addSchedule(Schedule schedule);
    int updateScheduleByuserid(Schedule schedule);
    int deleteScheduleByuserid(int userid);
}
