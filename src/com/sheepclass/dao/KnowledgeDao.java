package com.sheepclass.dao;

import com.sheepclass.entity.Knowledge;

public interface KnowledgeDao {
    Knowledge getKnowledgeByid(int knowledgeid);
    int addKnowledge(Knowledge knowledge);
    int updateKnowledge(Knowledge knowledge);
    int deleteKnowledgeByid(int knowledgeid);
}
