package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/*
    课程模块servlet
 */

@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    CourseService cs = new CourseServiceImpl();


    public void findCourseList(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Course> courseList = cs.findCourseList();
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                    "id","course_name","price","sort_num","status");
            String result = JSON.toJSONString(courseList,filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findByCourseNameAndStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            String courseName = request.getParameter("course_name");
            String status = request.getParameter("status");
            List<Course> courseList = cs.findByCourseNameAndStatus(courseName,status);
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                    "id","course_name","price","sort_num","status");
            String result = JSON.toJSONString(courseList,filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findCourseById(HttpServletRequest request, HttpServletResponse response){
        try {
            String id = request.getParameter("id");
            Course course = cs.findCourseById(Integer.parseInt(id));
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                    "id","course_name","brief","teacher_name",
                    "teacher_info","preview_first_field","preview_second_field","discounts","price","price_tag","share_image_title","share_title","share_description","course_description");
            String result = JSON.toJSONString(course,filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  updateCourseStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            String id = request.getParameter("id");
            Course course = cs.findCourseById(Integer.parseInt(id));
            int status = course.getStatus();
            if (status==0){
                course.setStatus(1);
            }else {
                course.setStatus(0);
            }
            course.setUpdate_time(DateUtils.getDateFormart());
            Map<String, Integer> map = cs.updateCourseStatus(course);
            String result = JSON.toJSONString(map);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
