package com.sheepclass.dao.implement;

import com.sheepclass.dao.LearninginfoDao;
import com.sheepclass.entity.Learninginfo;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class LearninginfoDaoimple implements LearninginfoDao {
    @Override
    public List<Learninginfo> getLearningtime(int userid,long time) {
        String sql = "select * from learninginfo where userid = "+userid+" and logintime>= "+time;
        return JdbcUtils.getList(Learninginfo.class,sql);
    }

    @Override
    public int getTimesoflearning(int userid,long time) {
        String sql = "select * from learninginfo where userid = "+userid+" and logintime>= "+time;
        return JdbcUtils.getListCount(sql);
    }

    @Override
    public int addInfo(Learninginfo learninginfo) {
        String sql = "insert into learninginfo values(null,?,?,?)";
        return JdbcUtils.executeSQL(sql,learninginfo.getUserid(),learninginfo.getLogintime(),learninginfo.getLogouttime());
    }

    @Override
    public int updateInfo(Learninginfo learninginfo) {
        String sql = "update learninginfo where key = ? set logouttime = ?";
        return JdbcUtils.executeSQL(sql,learninginfo.getKey(),learninginfo.getLogouttime());
    }

    @Override
    public int deleteBytime(long time) {
        String sql = "delete from learninginfo where time< = ?";
        return JdbcUtils.executeSQL(sql,time);
    }

    @Override
    public int deleteByuserid(int userid) {
        String sql = "delete from learninginfo where userid = ?";
        return JdbcUtils.executeSQL(sql,userid);
    }
}
