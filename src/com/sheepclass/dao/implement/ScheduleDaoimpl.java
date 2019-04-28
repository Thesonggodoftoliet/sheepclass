package com.sheepclass.dao.implement;

import com.sheepclass.dao.ScheduleDao;
import com.sheepclass.entity.Schedule;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class ScheduleDaoimpl implements ScheduleDao {
    @Override
    public List<Schedule> getScheduleByuserid(int userid) {
        String sql = "select * from _schedule where userid = "+userid;
        return JdbcUtils.getList(Schedule.class,sql);
    }

    @Override
    public Schedule getScheduleBycourseid(int userid, int courseid) {
        String sql = "select * from _schedule where userid = ? and courseid = ?";
        return (Schedule)JdbcUtils.getObject(Schedule.class,sql,userid,courseid);
    }

    @Override
    public int addSchedule(Schedule schedule) {
        String sql = "insert into _schedule values(?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,schedule.getSerialnum(),schedule.getCourseid(),schedule.getUserid(),schedule.getBreaktime(),schedule.getFinish());
    }

    @Override
    public int updateScheduleBycourseid(Schedule schedule) {
        String sql = "update _schedule set serialnum = ?,breaktime=?,finish=? where courseid=? and userid =?";
        return JdbcUtils.executeSQL(sql,schedule.getSerialnum(),schedule.getBreaktime(),schedule.getFinish(),schedule.getCourseid(),schedule.getUserid());
    }

    @Override
    public int deleteScheduleByuserid(int userid) {
        String sql = "delete from _schedule where userid=?";
        return JdbcUtils.executeSQL(sql,userid);
    }
}
