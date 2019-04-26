package com.sheepclass.dao.implement;

import com.sheepclass.dao.CourseDao;
import com.sheepclass.entity.Course;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class CourseDaoImle implements CourseDao {
    @Override
    public Course getcourseByid(int courseid) {
        String sql = "select * from course where courseid = ?";
        return (Course)JdbcUtils.getObjectById(Course.class,sql,courseid);
    }

    @Override
    public List<Course> getAllCourse() {
        String sql = "select * from course";
        return JdbcUtils.getList(Course.class,sql);
    }

    @Override
    public List<Course> getCoursesBysub(String subject) {
        String sql = "select * from course where subject = "+subject;
        return JdbcUtils.getList(Course.class,sql);
    }

    @Override
    public int addCourse(Course course) {
        String sql = "insert into course values(?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,null,course.getCoursename(),course.getInfo(),course.getSubject(),course.getImg());
    }

    @Override
    public int setCourse(Course course) {
        String sql = "update course where courseid =? set coursename=?,info = ?, subject = ?,img = ?";
        return JdbcUtils.executeSQL(sql,course.getCourseid(),course.getCoursename(),course.getInfo(),course.getSubject(),course.getImg());
    }

    @Override
    public int deleteCourse(int courseid) {
        String sql = "delete form course where courseid = ?";
        return JdbcUtils.executeSQL(sql,courseid);
    }
}
