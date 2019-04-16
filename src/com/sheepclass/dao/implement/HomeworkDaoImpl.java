package com.sheepclass.dao.implement;

import com.sheepclass.dao.HomeworkDao;
import com.sheepclass.entity.Homework;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class HomeworkDaoImpl implements HomeworkDao {
    @Override
    public Homework getHomeworkById(int id) {
        String sql = "select * from homework where homeworkid = ?";
        return (Homework) JdbcUtils.getObjectById(Homework.class,sql,id);
    }

    @Override
    public List<Homework> getHomeworkByknow(int knowledgeid) {
        String sql = "select * from knowledge_view_"+knowledgeid;
        return JdbcUtils.getList(Homework.class,sql);
    }

    @Override
    public int addHomework(Homework homework) {
        String sql = "insert into homework values(?,?,?,?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,homework.getHomeworkid(),homework.getA(),homework.getB(),homework.getC(),homework.getD(),homework.getCorrect(),homework.getContent(),homework.getSets());
    }

    @Override
    public int updateHomework(Homework homework) {
        String sql = "update homework where homeworkid =? set a=?,b=?,c=?,d=?,correct=?,content=?,set=?";
        return JdbcUtils.executeSQL(sql,homework.getHomeworkid(),homework.getA(),homework.getB(),homework.getC(),homework.getD(),homework.getCorrect(),homework.getContent(),homework.getSets());
    }

    @Override
    public int deleteHomework(int homeworkid) {
        String sql = "delete from homework where homeworkid = ?";
        return JdbcUtils.executeSQL(sql,homeworkid);
    }
}
