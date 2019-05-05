package com.sheepclass.dao.implement;

import com.sheepclass.dao.KnowledgeDao;
import com.sheepclass.entity.Knowledge;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class KnowledgeDaoImpl implements KnowledgeDao {
    @Override
    public Knowledge getKnowledgeByid(int knowledgeid) {
        String sql = "select * from knowledge where knowledgeid = ?";
        return (Knowledge) JdbcUtils.getObjectById(Knowledge.class,sql,knowledgeid);
    }

    @Override
    public List<Knowledge> getKnowledge(String content) {
        String sql = "select * from knowledge where content like '%"+content+"%' order by"
                +"(case when content = '"+content+"' then 1 "
                +"when content like '"+content+"%' then 2"
                +"when content like '%"+content+"' then 3"
                +"when content like '%"+content+"%' then 4"
                +"else 0 end) limit 0,50;";
        return JdbcUtils.getList(Knowledge.class,sql);
    }

    @Override
    public int createViewByid(int knowledgeid) {
        String sql = "create or replace view knowledge_view_? as select * from homework where sets like '[?][?,][,?,][,?]'";
        return JdbcUtils.executeSQL(sql,knowledgeid,knowledgeid,knowledgeid,knowledgeid,knowledgeid);
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
