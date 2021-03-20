package com.lagou.dao;

import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;

import java.util.List;

/*
 课程章节模块
 */
public interface CourseContentDao {
    //根据课程ID查询课程相关信息
    List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    //根据章节ID 查询章节相关的课时信息
    List<Course_Lesson> findLessonBySectionId(int sectionId);

    //添加章节时进行数据回显
    Course findCourseByCourseId(int courseId);

    //保存章节信息
    int saveSection(Course_Section section);
    //修改章节信息
    int updateSection(Course_Section section);
    //修改章节的状态
    int updateSectionStatus(int id,int status);

    //保存课时信息
    int saveLesson(Course_Lesson lesson);
    //修改课时信息
    int updateLesson(Course_Lesson lesson);
}
