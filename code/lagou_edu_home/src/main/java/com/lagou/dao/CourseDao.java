package com.lagou.dao;

import com.lagou.pojo.Course;

import java.util.List;

/*
    课程模块
 */
public interface CourseDao {
    //课程列表
    List<Course> findCourseList();
    //课程名，状态返回课程列表
    List<Course> findByCourseNameAndStatus(String courseName,String status);
    //添加课程
    int saveCourseSalesInfo(Course course);
    //根据课程ID 查询课程信息
    Course findCourseById(int id);
    //修改课程营销信息
    int updateCourseSalesInfo(Course course);
    //修改课程状态
    int updateCourseStatus(Course course);
}
