package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDao;
import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    课程管理模块Service层实现类
 */
public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        List<Course> courseList = courseDao.findCourseList();
        return courseList;
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        List<Course> courseList = courseDao.findByCourseNameAndStatus(courseName, status);
        return courseList;
    }

    @Override
    public String saveCourseSalesInfo(Course course) {
        int i = courseDao.saveCourseSalesInfo(course);
        String result;
        if (i>0){
            result = StatusCode.SUCCESS.toString();
        }else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }

    @Override
    public Course findCourseById(int id) {
        Course course = courseDao.findCourseById(id);
        return course;
    }

    @Override
    public String updateCourseSalesInfo(Course course) {
        int row = courseDao.updateCourseSalesInfo(course);
        String result;
        if (row>0){
            result = StatusCode.SUCCESS.toString();
        }else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {
        int row = courseDao.updateCourseStatus(course);
        Map<String,Integer> map = new HashMap<>();
        if (row>0){
            if(course.getStatus() == 0){
                map.put("status",0);
            }else{
                map.put("status",1);
            }
        }
        return map;
    }
}
