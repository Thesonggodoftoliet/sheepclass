package com.sheepclass.service;

import com.sheepclass.dao.CourseDao;
import com.sheepclass.dao.LearninginfoDao;
import com.sheepclass.dao.MistakesDao;
import com.sheepclass.dao.UserDao;
import com.sheepclass.dao.implement.CourseDaoImle;
import com.sheepclass.dao.implement.LearninginfoDaoimple;
import com.sheepclass.dao.implement.MistakesDaoImpl;
import com.sheepclass.dao.implement.UserDaoIm;
import com.sheepclass.entity.Course;
import com.sheepclass.entity.Mistakes;
import com.sheepclass.entity.Users;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class InfoGet {
    //学情管理，包含预警
    //七天一个周期
    public int getWeeklogintimes(int userid){
        Calendar calendar = Calendar.getInstance();
        calendar.getFirstDayOfWeek();
        LearninginfoDao learninginfoDao = new LearninginfoDaoimple();
        calendar.add(Calendar.DATE,-calendar.getTime().getDay());
        return learninginfoDao.getTimesoflearning(userid,calendar.getTimeInMillis());
    }

    public long getWeekTottime(int userid){//获取一周的学习时长
        UserDao userDao = new UserDaoIm();
        return userDao.getUserById(userid).getTot_time();
    }

    public List<Course> getMosterrorCourse(int userid){//获取错题较多的三个课程
        class temp implements Comparable{
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
                temp p = (temp)o;
                return p.num - this.num;
            }
        }
        MistakesDao mistakesDao = new MistakesDaoImpl();
        List<Mistakes> mistakes = mistakesDao.getMistakesByuserid(userid);
        List<temp> temps = new ArrayList<>();
        for (int i = 0;i<mistakes.size();i++){
            for (int j = 0;j<temps.size();j++)
                if (mistakes.get(i).getCourseid() == temps.get(j).getCourseid())
                    temps.get(j).setNum(temps.get(j).getNum()+1);
            temp t = new temp();
            t.setCourseid(mistakes.get(i).getCourseid());
            t.setNum(1);
            temps.add(t);
        }
        Collections.sort(temps);
        List<Course> courses = new ArrayList<>();
        CourseDao courseDao = new CourseDaoImle();
        for (int i = 0;i<3;i++){
            courses.add(courseDao.getcourseByid(temps.get(i).getCourseid()));
        }
        return courses;
    }

}
