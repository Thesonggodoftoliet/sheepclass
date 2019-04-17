package com.sheepclass.service;

import com.sheepclass.dao.*;
import com.sheepclass.dao.implement.*;
import com.sheepclass.entity.*;
import com.sheepclass.utils.PraseUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*学习板块，包括视频学习，错题学习*/
public class Learn {
    private CourseDao courseDao = null;
    private ChapterDao chapterDao = null;
    private ScheduleDao scheduleDao = null;
    private HomeworkDao homeworkDao = null;
    private MistakesDao mistakesDao = null;

    class Temp implements Comparable{
        private int homeworkid;
        private int num;
        private int level;
        private Homework homework;

        public Homework getHomework() {
            return homework;
        }

        public void setHomework(Homework homework) {
            this.homework = homework;
        }

        public int getHomeworkid() {
            return homeworkid;
        }

        public void setHomeworkid(int homeworkid) {
            this.homeworkid = homeworkid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public int compareTo(Object o) {
            Temp p = (Temp)o;
            return p.num-this.num;//降序排列
        }
    }
    List<Course> getAllcourse(){
        if (courseDao == null)
            courseDao = new CourseDaoImle();
        return courseDao.getAllCourse();
    }

    List<Course> getCoursesBysub(String subject){
        if (courseDao == null)
            courseDao = new CourseDaoImle();
        return courseDao.getCoursesBysub(subject);
    }

    List<Chapter> getChapterByCourse(int courseid){
        if (chapterDao == null)
            chapterDao = new ChapterDaoImpl();
        return chapterDao.getChapterByCourse(courseid);
    }

    Chapter getChapter(Chapter chapter){
        if (chapterDao == null)
            chapterDao = new ChapterDaoImpl();
        return chapterDao.getChapterByChapterId(chapter.getSerialnum(),chapter.getCourseid());
    }

    Schedule getSchedule(int userid){
        if (scheduleDao == null)
            scheduleDao = new ScheduleDaoimpl();
        return scheduleDao.getScheduleByuserid(userid);
    }

    int updateSchedule(Schedule schedule){
        if (scheduleDao == null)
            scheduleDao = new ScheduleDaoimpl();
        return scheduleDao.updateScheduleByuserid(schedule);
    }

    List<Homework> generateHomework(String sets){
        if (homeworkDao == null)
            homeworkDao = new HomeworkDaoImpl();
        List<Integer> knowledges = PraseUtils.sToi(sets);
        List<Homework> homeworkList = new ArrayList<>();
        List<Temp> temps = new ArrayList<>();
        for (int i = 0;i<knowledges.size();i++){//循环遍历加入题目
            homeworkList.addAll(homeworkDao.getHomeworkByknow(knowledges.get(i)));
        }
        Collections.sort(homeworkList);

        //去重
        for (int j = 0;j<homeworkList.size();j++){
            Temp temp = new Temp();
            temp.setHomeworkid(homeworkList.get(j).getHomeworkid());
            temp.setLevel(homeworkList.get(j).getLevel());
            int last = homeworkList.lastIndexOf(homeworkList.get(j));//最后一次出现位置
            temp.setNum(last-j+1);//知识点符合程度
            temp.setHomework(homeworkList.get(j));
            temps.add(temp);
            if (j != last)
                for (int m = j+1;m<=last-j;m++)
                    homeworkList.remove(j+1);
        }
        homeworkList.clear();
        Collections.sort(temps);

        /*选题*/
        /*假设十道题，选五道简单题，四道中等题，一道困难题*/
        int easy = 0;
        int middle= 0;
        int hard = 0;
        for (int i =0;i<temps.size();i++){
            Temp temp = temps.get(i);
            if (temp.getLevel()>=10 && hard <1) {
                hard ++;
                homeworkList.add(temp.getHomework());
            }
            if (temp.getLevel()>=6 && temp.getLevel()<10 && middle<4){
                middle++;
                homeworkList.add(temp.getHomework());
            }
            if (temp.getLevel()<6 && easy <5){
                easy++;
                homeworkList.add(temp.getHomework());
            }

            if (hard == 1 && easy == 5 && middle ==4)
                break;
        }

        return homeworkList;
    }

    List<Homework> getMistakesByuserid(int userid){
        /*（待做）传回的时候应当加上课程名等*/
        if (mistakesDao == null)
            mistakesDao = new MistakesDaoImpl();
        if (homeworkDao == null)
            homeworkDao = new HomeworkDaoImpl();
        List<Mistakes> mistakes = mistakesDao.getMistakesByuserid(userid);
        List<Homework> homeworkList = new ArrayList<>();
        for (int i = 0;i<mistakes.size();i++){
            homeworkList.add(homeworkDao.getHomeworkById(mistakes.get(i).getHomeworkid()));
        }
        return homeworkList;
    }
}
