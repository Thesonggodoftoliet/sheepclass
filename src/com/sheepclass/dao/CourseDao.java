package com.sheepclass.dao;

import com.sheepclass.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourse();
    List<Course> getCoursesBysub(String subject);
    int addCourse(Course course);
    int setCourse(Course course);
    int deleteCourse(int courseid);
}
