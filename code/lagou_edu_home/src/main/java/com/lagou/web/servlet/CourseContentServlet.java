package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    CourseContentService contentService = new CourseContentServiceImpl();

     public void findSectionAndLessonByCourseId(HttpServletRequest request, HttpServletResponse response){
         try {
             String course_id = request.getParameter("course_id");
             List<Course_Section> sectionList = contentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));
             SimplePropertyPreFilter filter = new SimplePropertyPreFilter();

             String result = JSON.toJSONString(sectionList);
             response.getWriter().print(result);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void findCourseById(HttpServletRequest request, HttpServletResponse response){
         try {
             String course_id = request.getParameter("course_id");
             Course course = contentService.findCourseById(Integer.parseInt(course_id));

             String result = JSON.toJSONString(course);
             response.getWriter().print(result);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void saveOrUpdateSection(HttpServletRequest request, HttpServletResponse response){
         try {
             //1.创建Course对象
             Course_Section section = new Course_Section();
             //2.创建Map集合，用来收集数据
             Map<String,Object> map = (Map)request.getAttribute("map");
             //3.使用BeanUtils 将map封装到bean
             BeanUtils.populate(section,map);
             String result;
             if (section.getId()!=0){   //修改章节信息
                  result= contentService.updateSection(section);
             }else {
                  result = contentService.saveSection(section);
             }
             response.getWriter().print(result);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public void updateSectionStatus(HttpServletRequest request, HttpServletResponse response){
         try {
             String id = request.getParameter("id");
             String status = request.getParameter("status");

             String result = contentService.updateSectionStatus(Integer.parseInt(id), Integer.parseInt(status));

             response.getWriter().print(result);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void saveOrUpdateLesson(HttpServletRequest request, HttpServletResponse response){
         try {
             Course_Lesson lesson = new Course_Lesson();
             Map<String,Object> map =(Map) request.getAttribute("map");
             BeanUtils.populate(lesson,map);
             String result;
             if (lesson.getId()!=0){
                 result = contentService.updateLesson(lesson);
             }else {
                 result = contentService.saveLesson(lesson);
             }
             response.getWriter().print(result);
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
