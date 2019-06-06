package com.sheepclass.service;

import com.sheepclass.dao.*;
import com.sheepclass.dao.implement.*;
import com.sheepclass.entity.*;
import com.sheepclass.utils.PraseUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class InfoGet {
    //学情管理，包含预警
    //七天一个周期


    public int getWeeklogintimes(int userid){//本周打卡的次数
        Calendar calendar = Calendar.getInstance();
        calendar.getFirstDayOfWeek();
        LearninginfoDao learninginfoDao = new LearninginfoDaoimple();
        calendar.add(Calendar.DATE,-calendar.getTime().getDay());
        return learninginfoDao.getTimesoflearning(userid,calendar.getTimeInMillis());
    }

    public int deleteLearn(){//删除两周之前的数据
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-14);
        LearninginfoDao learninginfoDao = new LearninginfoDaoimple();
        return learninginfoDao.deleteBytime(calendar.getTimeInMillis());
    }

    public long getWeekTottime(int userid){//获取一周的学习时长
        UserDao userDao = new UserDaoIm();
        return userDao.getUserById(userid).getTot_time();
    }

    public List<Course> getMosterrorCourse(int userid){//获取错题较多的三个课程
        class tempone implements Comparable{
            private int courseid;
            private int num;

            public int getCourseid() {
                return courseid;
            }

            public void setCourseid(int courseid) {
                this.courseid = courseid;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            @Override
            public int compareTo(Object o) {
                tempone p = (tempone)o;
                return p.num - this.num;
            }
        }
        MistakesDao mistakesDao = new MistakesDaoImpl();
        List<Mistakes> mistakes = mistakesDao.getMistakesByuserid(userid);
        List<tempone> temps = new ArrayList<>();
        for (int i = 0;i<mistakes.size();i++){
            for (int j = 0;j<temps.size();j++)
                if (mistakes.get(i).getCourseid() == temps.get(j).getCourseid())
                    temps.get(j).setNum(temps.get(j).getNum()+1);
            tempone t = new tempone();
            t.setCourseid(mistakes.get(i).getCourseid());
            t.setNum(1);
            temps.add(t);
        }
        Collections.sort(temps);
        List<Course> courses = new ArrayList<>();
        CourseDao courseDao = new CourseDaoImle();
        for (int i = 0;i<3;i++){
            if (temps.size()==i)
                break;
            courses.add(courseDao.getcourseByid(temps.get(i).getCourseid()));
        }
        return courses;
    }

    public List<Knowledge> getMostwrongKnowledges(int userid) {//错得最多的知识点
        class Temp implements Comparable{
            int id;
            int count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            @Override
            public int compareTo(Object o) {
                Temp p = (Temp)o;
                return p.count-this.count;
            }
        }
        MistakesDao mistakesDao = new MistakesDaoImpl();
        HomeworkDao homeworkDao = new HomeworkDaoImpl();
        KnowledgeDao knowledgeDao = new KnowledgeDaoImpl();
        List<Mistakes> mistakes = mistakesDao.getMistakesByuserid(userid);
        List<Homework> homeworks = new ArrayList<>();
        List<Integer> point = new ArrayList<>();
        for (int i = 0; i < mistakes.size(); i++) {
            homeworks.add(homeworkDao.getHomeworkById(mistakes.get(i).getHomeworkid()));
        }

        for (int j = 0; j < homeworks.size(); j++) {
            point.addAll(PraseUtils.sToi(homeworks.get(j).getSets()));//知识点编号集
        }

        Collections.sort(point);
        List<Temp>temps = new ArrayList<>();
        for (int m =0;m<point.size();m++){
            Temp temp = new Temp();
            temp.setId(point.get(m));
            int last = point.lastIndexOf(temp.getId());
            temp.setCount(last-m+1);
            m=last+1;
            temps.add(temp);
        }
        List<Knowledge> knowledges = new ArrayList<>();
        for (int i=0;i<5;i++){
            if (temps.size()==i)
                break;
            knowledges.add(knowledgeDao.getKnowledgeByid(temps.get(i).getId()));
        }
        return knowledges;
    }

    List<Users> getParents(){
        UserDao userDao = new UserDaoIm();
        return userDao.getUsersByIdentity(3);
    }

    List<Users> getStudents(){
        UserDao userDao = new UserDaoIm();
        return userDao.getUsersByIdentity(2);
    }

    public float rateofWrong(int courseid,int userid){//每门课的错题率
        //根据学生的学习进度加上每门课的章节数（每章十道题）来统计
        MistakesDao mistakesDao = new MistakesDaoImpl();
        List<Mistakes> mistakes = mistakesDao.getMistakesBycourse(userid,courseid);
        ScheduleDao scheduleDao = new ScheduleDaoimpl();
        Schedule schedule = scheduleDao.getScheduleBycourseid(userid,courseid);
        ChapterDao chapterDao = new ChapterDaoImpl();
        List<Chapter> chapters = chapterDao.getChapterByCourse(courseid);
        int num = 0;
        System.out.println("size"+chapters.size());
        if (schedule.getFinish() == 1) {
            num = chapters.size();
        }else {
            for (int i = 0; i < chapters.size(); i++) {
                System.out.println("chapters.get(i).getSerialnum()"+chapters.get(i).getSerialnum());
                System.out.println("schedule.getSerialnum()"+schedule.getSerialnum());
                if (chapters.get(i).getSerialnum() < schedule.getSerialnum())
                    num++;
            }
        }
        return (float) (mistakes.size()/(num*10));
    }

    public List<Course> Coursesison(int userid){//正在上的课程
        ScheduleDao scheduleDao = new ScheduleDaoimpl();
        List<Schedule> schedules = scheduleDao.getScheduleByuserid(userid);
        for (int i = 0;i<schedules.size();i++){
            if (schedules.get(i).getFinish() == 1)
                schedules.remove(i);
        }
        CourseDao courseDao = new CourseDaoImle();
        List<Course> courses = new ArrayList<>();
        for (int i=0;i<schedules.size();i++){
            courses.add(courseDao.getcourseByid(schedules.get(i).getCourseid()));
        }
        return courses;
    }

    public List<Course> Coursewason(int userid){//上过的课程
        ScheduleDao scheduleDao = new ScheduleDaoimpl();
        List<Schedule> schedules = scheduleDao.getScheduleByuserid(userid);
        for (int i = 0;i<schedules.size();i++){
            if (schedules.get(i).getFinish() == 0)
                schedules.remove(i);
        }
        CourseDao courseDao = new CourseDaoImle();
        List<Course> courses = new ArrayList<>();
        for (int i=0;i<schedules.size();i++){
            courses.add(courseDao.getcourseByid(schedules.get(i).getCourseid()));
        }
        return courses;

    }

}
