package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseContentDao;
import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.utils.DateUtils;

import java.util.List;

public class CourseContentServiceImpl implements CourseContentService {

    CourseContentDao courseContentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        List<Course_Section> sectionList = courseContentDao.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    @Override
    public Course findCourseById(int courseId) {
        Course course = courseContentDao.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public String saveSection(Course_Section section) {
        section.setStatus(2);//状态，0:隐藏；1：待更新；2：已发布
        String date = DateUtils.getDateFormart();
        section.setCreate_time(date);
        section.setUpdate_time(date);

        int row = courseContentDao.saveSection(section);

        String result;
        if (row>0){
            result = StatusCode.SUCCESS.toString();
        }else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }

    @Override
    public String updateSection(Course_Section section) {
        //1.补全章节信息
        String date = DateUtils.getDateFormart();
        section.setUpdate_time(date);
        //2.更改
        int row = courseContentDao.updateSection(section);
        String result;
        if (row>0){
            result = StatusCode.SUCCESS.toString();
        }else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }

    @Override
    public String updateSectionStatus(int id, int status) {
        int row = courseContentDao.updateSectionStatus(id, status);
        String result;
        if (row>0){
            result = StatusCode.SUCCESS.toString();
        }else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }

    @Override
    public String saveLesson(Course_Lesson lesson) {
        lesson.setStatus(2);
        lesson.setCreate_time(DateUtils.getDateFormart());
        lesson.setUpdate_time(DateUtils.getDateFormart());
        int row = courseContentDao.saveLesson(lesson);
        String result;
        if (row>0){
            result = StatusCode.SUCCESS.toString();
        }else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }

    @Override
    public String updateLesson(Course_Lesson lesson) {
        lesson.setUpdate_time(DateUtils.getDateFormart());
        int row = courseContentDao.updateLesson(lesson);
        String result;
        if (row > 0) {
            result = StatusCode.SUCCESS.toString();
        } else {
            result = StatusCode.Fail.toString();
        }
        return result;
    }
}
