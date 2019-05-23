package com.sheepclass.dao.implement;

import com.sheepclass.dao.ChapterDao;
import com.sheepclass.entity.Chapter;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class ChapterDaoImpl implements ChapterDao {
    @Override
    public List<Chapter> getChapterByCourse(int courseid) {
        String sql = "select * from chapter where courseid = "+courseid;
        return JdbcUtils.getList(Chapter.class,sql);
    }

    @Override
    public Chapter getChapterByChapterId(float serialnum,int courseid) {
        String sql = "select * from chapter where serialnum = ? and courseid = ?";
        return (Chapter) JdbcUtils.getObject(Chapter.class,sql,serialnum,courseid);
    }

    @Override
    public int addChapter(Chapter chapter) {
        String sql = "insert into chapter values(?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql);
    }

    @Override
    public int setChapterByChapterId(Chapter chapter) {
        String sql = "update chapter where serialnum = ? and courseid = ? set serialnum = ?,courseid = ?,chaptername=?,vedio=?,sets=?";
        return JdbcUtils.executeSQL(sql);
    }

    @Override
    public int deleteChapterByChapterId(float serialnum , int courseid) {
        String sql = "delete from chapter where serialnum = ? and courseid = ?";
        return JdbcUtils.executeSQL(sql);
    }

    @Override
    public int numofChapter(int courseid) {
        return 0;
    }
}
