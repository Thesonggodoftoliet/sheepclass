package com.sheepclass.dao;


import com.sheepclass.entity.Chapter;

import java.util.List;

public interface ChapterDao {
    List<Chapter> getChapterByCourse(int courseid);
    Chapter getChapterByChapterId(float serialnum,int courseid);
    int addChapter(Chapter chapter);
    int setChapterByChapterId(Chapter chapter);
    int deleteChapterByChapterId(float serialnum,int courseid);
    int numofChapter(int courseid);
}
