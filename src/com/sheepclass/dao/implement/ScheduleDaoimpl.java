package com.sheepclass.dao.implement;

import com.sheepclass.dao.ScheduleDao;
import com.sheepclass.entity.Schedule;
import com.sheepclass.utils.JdbcUtils;

public class ScheduleDaoimpl implements ScheduleDao {
    @Override
    public Schedule getScheduleByuserid(int userid) {
        String sql = "select * from _schedule where userid = ?";
        return (Schedule) JdbcUtils.getObjectById(Schedule.class,sql,userid);
    }

    @Override
    public int addSchedule(Schedule schedule) {
        String sql = "insert into _schedule values(?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,schedule.getSerialnum(),schedule.getCourseid(),schedule.getUserid(),schedule.getBreaktime(),schedule.getFinish());
    }

    @Override
    public int updateScheduleByuserid(Schedule schedule) {
        String sql = "update _schedule set serialnum = ?,courseid=?,breaktime=?,finish where userid=?";
        return JdbcUtils.executeSQL(sql,schedule.getSerialnum(),schedule.getCourseid(),schedule.getBreaktime(),schedule.getFinish());
    }

    @Override
    public int deleteScheduleByuserid(int userid) {
        String sql = "delete from _schedule where userid=?";
        return JdbcUtils.executeSQL(sql,userid);
    }
}
