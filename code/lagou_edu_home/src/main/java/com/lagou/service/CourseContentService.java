package com.lagou.service;

import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;

import java.util.List;

public interface CourseContentService {
    List<Course_Section> findSectionAndLessonByCourseId(int courseId);
    Course findCourseById(int courseId);
    String saveSection(Course_Section section);
    String updateSection(Course_Section section);
    String updateSectionStatus(int id,int status);

    String saveLesson(Course_Lesson lesson);
    String updateLesson(Course_Lesson lesson);
}
