package com.sheepclass.dao.implement;

import com.sheepclass.dao.MistakesDao;
import com.sheepclass.entity.Mistakes;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class MistakesDaoImpl implements MistakesDao {
    @Override
    public int createViewByuserid(int userid) {
        String sql = "create or replace view mistake_view_"+userid+" as select * from mistakes where userid = "+userid;
        return JdbcUtils.executeSQL(sql);
    }

    @Override
    public Mistakes getMistakesById(Mistakes mistakes) {
        String sql = "select * from mistake_view_? where courseid=? and homeworkid=?";
        return (Mistakes) JdbcUtils.getObject(Mistakes.class,sql,mistakes.getUserid(),mistakes.getCourseid(),mistakes.getHomeworkid());
    }

    @Override
    public List<Mistakes> getMistakesByuserid(int userid) {
        String sql = "select * from mistake_view_"+userid;
        return JdbcUtils.getList(Mistakes.class,sql);
    }

    @Override
    public List<Mistakes> getMistakesBycourse(int userid, int courseid) {
        String slq = "select * from mistake_view_"+userid+" where courseid="+courseid;
        return JdbcUtils.getList(Mistakes.class,slq);
    }

    @Override
    public int addMistakes(Mistakes mistakes) {
        String sql = "insert into mistakes values(?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,mistakes.getUserid(),mistakes.getHomeworkid(),mistakes.getCourseid(),mistakes.getReviewtimes(),mistakes.getWrongtimes());
    }

    @Override
    public int updateMistakes(Mistakes mistakes) {
        String sql = "update mistakes set reviewtimes = ?,wrongtimes = ? where userid = ? and courseid = ? and homeworkid=?";
        return JdbcUtils.executeSQL(sql,mistakes.getReviewtimes(),mistakes.getWrongtimes(),mistakes.getUserid(),mistakes.getCourseid(),mistakes.getHomeworkid());
    }

    @Override
    public int deleteMistakesByhomeworkid(int userid, int homeworkid) {
        String sql = "delete from mistakes where userid = ? and homeworkid = ?";
        return JdbcUtils.executeSQL(sql,userid,homeworkid);
    }
}
