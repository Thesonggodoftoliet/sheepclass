package com.sheepclass.dao;

import com.sheepclass.entity.Learninginfo;

import java.util.List;

public interface LearninginfoDao {
    List<Learninginfo> getLearningtime(int userid,long time);
    int getTimesoflearning(int userid,long time);
    int addInfo(Learninginfo learninginfo);
    int updateInfo(Learninginfo learninginfo);
    int deleteBytime(int userid,long time);
    int deleteByuserid(int userid);
}
