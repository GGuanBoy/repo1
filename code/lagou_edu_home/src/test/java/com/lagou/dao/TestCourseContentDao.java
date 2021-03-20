package com.lagou.dao;

import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCourseContentDao {
    CourseContentDao courseContentDao = new CourseContentDaoImpl();

    @Test
    public void testFindSectionAndLessonByCourseId(){
        List<Course_Section> sectionList = courseContentDao.findSectionAndLessonByCourseId(1);
        for (Course_Section cs:sectionList){
            System.out.println(cs.getSection_name());
            for (Course_Lesson cl:cs.getLessonList()){
                System.out.println(cl.getTheme());
            }
        }
    }
}
