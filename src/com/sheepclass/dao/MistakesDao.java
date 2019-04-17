package com.sheepclass.dao;

import com.sheepclass.entity.Mistakes;

import java.util.List;

public interface MistakesDao {
    int createViewByuserid(int userid);//创建视图
    Mistakes getMistakesById(Mistakes mistakes);
    List<Mistakes> getMistakesByuserid(int userid);
    List<Mistakes> getMistakesBycourse(int userid,int courseid);
    int addMistakes(Mistakes mistakes);
    int updateMistakes(Mistakes mistakes);
    int deleteMistakesByhomeworkid(int userid,int homeworkid);
}
