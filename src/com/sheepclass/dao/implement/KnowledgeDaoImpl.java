package com.sheepclass.dao.implement;

import com.sheepclass.dao.KnowledgeDao;
import com.sheepclass.entity.Knowledge;
import com.sheepclass.utils.JdbcUtils;

public class KnowledgeDaoImpl implements KnowledgeDao {
    @Override
    public Knowledge getKnowledgeByid(int knowledgeid) {
        String sql = "select * from knowledge where knowledgeid = ?";
        return (Knowledge) JdbcUtils.getObjectById(Knowledge.class,sql,knowledgeid);
    }

    @Override
    public int addKnowledge(Knowledge knowledge) {
        String sql = "insert into knowledge values(?,?,?)";
        return JdbcUtils.executeSQL(sql,knowledge.getKnowledgeid(),knowledge.getContent(),knowledge.getLevel());
    }

    @Override
    public int updateKnowledge(Knowledge knowledge) {
        String sql = "update knowledge set content = ?,level = ? where knowledgeid = ?";
        return JdbcUtils.executeSQL(sql,knowledge.getContent(),knowledge.getLevel(),knowledge.getKnowledgeid());
    }

    @Override
    public int deleteKnowledgeByid(int knowledgeid) {
        String sql = "delete from knowledge where knowledgeid =?";
        return JdbcUtils.executeSQL(sql,knowledgeid);
    }
}
