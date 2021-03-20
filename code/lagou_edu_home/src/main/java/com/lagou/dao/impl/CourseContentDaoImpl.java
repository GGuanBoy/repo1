package com.lagou.dao.impl;

import com.lagou.dao.CourseContentDao;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.utils.DateUtils;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CourseContentDaoImpl implements CourseContentDao {


    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT id,course_id,section_name,description,order_num,status FROM course_section WHERE course_id =?";
            List<Course_Section> sectionList = qr.query(sql, new BeanListHandler<>(Course_Section.class), courseId);
            for (Course_Section section:sectionList){
                List<Course_Lesson> lessonList = findLessonBySectionId(section.getId());
                section.setLessonList(lessonList);
            }
            return sectionList;
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "select id,course_id,section_id,theme,duration,is_free,order_num,status from course_lesson where section_id=?";
            List<Course_Lesson> lessonList = qr.query(sql, new BeanListHandler<>(Course_Lesson.class), sectionId);
            return lessonList;
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT id,course_name FROM course WHERE id=?";
            Course course = qr.query(sql, new BeanHandler<>(Course.class), courseId);
            return course;
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "INSERT INTO course_section(course_id,section_name,description,order_num,STATUS,create_time,update_time) VALUES(?,?,?,?,?,?,?)";
            Object[] param = {section.getCourse_id(),section.getSection_name(),section.getDescription(), section.getOrder_num(),section.getStatus(),section.getCreate_time(),section.getUpdate_time()};
            int row = qr.update(sql, param);
            return row;
        } catch (SQLException s) {
            s.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "UPDATE course_section \n" +
                    "SET section_name =?,description=?,order_num=?,update_time=?\n" +
                    "WHERE id=?";
            Object[] params={section.getSection_name(),section.getDescription(),section.getOrder_num(),
            section.getUpdate_time(),section.getId()};
            int row = qr.update(sql, params);
            return row;
        } catch (SQLException s) {
            s.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSectionStatus(int id, int status) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "update course_section set status=?,update_time=? where id=?";
            Object[] params = {status, DateUtils.getDateFormart(),id};
            int row = qr.update(sql, params);
            return row;
        } catch (SQLException s) {
            s.printStackTrace();
            return 0;
        }
    }

    @Override
    public int saveLesson(Course_Lesson lesson) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "INSERT INTO course_lesson(course_id,section_id,theme,duration,is_free,order_num,STATUS,create_time,update_time)\n" +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            Object[] params ={lesson.getCourse_id(),lesson.getSection_id(),lesson.getTheme(),lesson.getDuration(),lesson.getIs_free(),lesson.getOrderNum(),lesson.getStatus(),
            lesson.getCreate_time(),lesson.getUpdate_time()};
            int row = qr.update(sql, params);
            return row;
        } catch (SQLException s) {
            s.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateLesson(Course_Lesson lesson) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "update course_lesson set theme=?,duration=?,is_free=?,order_num=?,update_time=? where id=?";
            Object[] params={lesson.getTheme(),lesson.getDuration(),lesson.getIs_free(),lesson.getOrderNum(),lesson.getUpdate_time(),lesson.getId()};
            int row = qr.update(sql, params);
            return row;
        } catch (SQLException s) {
            s.printStackTrace();
            return 0;
        }
    }
}
