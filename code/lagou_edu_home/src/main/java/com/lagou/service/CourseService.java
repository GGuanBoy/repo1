package com.lagou.service;

import com.lagou.pojo.Course;

import java.util.List;
import java.util.Map;

/*
    课程管理模块 Service
 */
public interface CourseService {
    List<Course> findCourseList();
    List<Course> findByCourseNameAndStatus(String courseName,String status);
    String saveCourseSalesInfo(Course course);
    Course findCourseById(int id);
    String updateCourseSalesInfo(Course course);
    Map<String,Integer> updateCourseStatus(Course course);
}
