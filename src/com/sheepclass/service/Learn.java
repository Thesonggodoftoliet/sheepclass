package com.sheepclass.service;

import com.sheepclass.dao.ChapterDao;
import com.sheepclass.dao.CourseDao;
import com.sheepclass.dao.implement.ChapterDaoImpl;
import com.sheepclass.dao.implement.CourseDaoImle;
import com.sheepclass.entity.Chapter;
import com.sheepclass.entity.Course;

import java.util.List;

/*学习板块，包括视频学习，错题学习*/
public class Learn {
    private CourseDao courseDao = new CourseDaoImle();
    private ChapterDao chapterDao = new ChapterDaoImpl();

    List<Course> getAllcourse(){
        return courseDao.getAllCourse();
    }

    List<Course> getCoursesBysub(String subject){
        return courseDao.getCoursesBysub(subject);
    }

    List<Chapter> getChapterByCourse(int courseid){
        return chapterDao.getChapterByCourse(courseid);
    }

    Chapter getChapter(Chapter chapter){
        return chapterDao.getChapterByChapterId(chapter.getSerialnum(),chapter.getCourseid());
    }
}
