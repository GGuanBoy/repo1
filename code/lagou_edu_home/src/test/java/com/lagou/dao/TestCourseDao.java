package com.lagou.dao;

import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCourseDao {
    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void textFindCourseList(){
        List<Course> courseList = courseDao.findCourseList();
        for (Course c:courseList){
            System.out.println(c);
        }
    }

    @Test
    public void textFindByCourseNameAndStatus(){
        List<Course> courseList = courseDao.findByCourseNameAndStatus("架构","");
        for (Course c:courseList){
            System.out.println(c);
        }
    }
}
