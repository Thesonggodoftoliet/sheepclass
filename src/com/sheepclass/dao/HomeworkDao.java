package com.sheepclass.dao;

import com.sheepclass.entity.Homework;

import java.util.List;

public interface HomeworkDao {
    Homework getHomeworkById(int id);
    List<Homework> getHomeworkByknow(int knowledgeid);//根据章节的知识点集查询
    int addHomework(Homework homework);
    int updateHomework(Homework homework);
    int deleteHomework(int homeworkid);
}
