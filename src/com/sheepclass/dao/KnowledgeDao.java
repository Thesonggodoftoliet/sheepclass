package com.sheepclass.dao;

import com.sheepclass.entity.Knowledge;

import java.util.List;

public interface KnowledgeDao {
    Knowledge getKnowledgeByid(int knowledgeid);
    List<Knowledge> getKnowledge(String content);
    int createViewByid(int knowledgeid);
    int addKnowledge(Knowledge knowledge);
    int updateKnowledge(Knowledge knowledge);
    int deleteKnowledgeByid(int knowledgeid);
}
