package com.lagou.dao.impl;

import com.lagou.dao.CourseDao;
import com.lagou.pojo.Course;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 课程模块 Dao层的实现类
 */
public class CourseDaoImpl implements CourseDao {
    @Override
    public List<Course> findCourseList() {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT \n" +
                "  id,course_name,price,sort_num,STATUS\n" +
                "FROM course WHERE is_del = ?";
        try {
            List<Course> list = qr.query(sql, new BeanListHandler<>(Course.class), 0);
            return list;
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            StringBuilder sb = new StringBuilder("SELECT id,course_name,price,sort_num,STATUS FROM course WHERE 1=1 AND is_del = ?");
            List<Object> list = new ArrayList<>();
            list.add(0);
            if (courseName!=null && courseName!=""){
                sb.append(" and course_name like ?");
                String name = "%" + courseName + "%";
                list.add(name);
            }
            if (status!=null && status!=""){
                sb.append(" and status = ?");
                int i = Integer.parseInt(status);
                list.add(i);
            }
            List<Course> courseList = qr.query(sb.toString(), new BeanListHandler<>(Course.class), list.toArray());
            return courseList;
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveCourseSalesInfo(Course course) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            //2.编写SQL
            String sql = "INSERT INTO course(\n" +
                    "course_name,\n" +
                    "brief,\n" +
                    "teacher_name,\n" +
                    "teacher_info,\n" +
                    "preview_first_field,\n" +
                    "preview_second_field,\n" +
                    "discounts,\n" +
                    "price,\n" +
                    "price_tag,\n" +
                    "share_image_title,\n" +
                    "share_title,\n" +
                    "share_description,\n" +
                    "course_description,\n" +
                    "course_img_url,\n" +
                    "STATUS,\n" +
                    "create_time,\n" +
                    "update_time\n" +
                    ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            //3.准备参数
            Object[] param = {course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
                    course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),
                    course.getPrice_tag(),course.getShare_image_title(),course.getShare_title(),course.getShare_description(),
                    course.getCourse_description(),course.getCourse_img_url(),course.getStatus(),course.getCreate_time(),course.getUpdate_time()};
            //4.执行插入操作
            int row = qr.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Course findCourseById(int id) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_name,\n" +
                    "brief,\n" +
                    "teacher_name,\n" +
                    "teacher_info,\n" +
                    "preview_first_field,\n" +
                    "preview_second_field,\n" +
                    "discounts,\n" +
                    "price,\n" +
                    "price_tag,\n" +
                    "course_img_url,\n" +
                    "share_image_title,\n" +
                    "share_title,\n" +
                    "share_description,\n" +
                    "course_description,\n" +
                    "STATUS\n" +
                    "FROM course WHERE id = ?;";

            Course course = qr.query(sql, new BeanHandler<Course>(Course.class), id);
            return course;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateCourseSalesInfo(Course course) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course SET \n" +
                    "course_name = ?,\n" +
                    "brief = ?,\n" +
                    "teacher_name = ?,\n" +
                    "teacher_info = ?,\n" +
                    "preview_first_field = ?,\n" +
                    "preview_second_field = ?,\n" +
                    "discounts = ?,\n" +
                    "price = ?,\n" +
                    "price_tag = ?,\n" +
                    "share_image_title = ?,\n" +
                    "share_title = ?,\n" +
                    "share_description = ?,\n" +
                    "course_description = ?,\n" +
                    "course_img_url = ?,\n" +
                    "update_time = ?\n" +
                    "WHERE id = ?";

            Object[] param = {course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
                    course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),course.getPrice_tag(),
                    course.getShare_image_title(),course.getShare_title(),course.getShare_description(),course.getCourse_description(),
                    course.getCourse_img_url(),course.getUpdate_time(),course.getId()};

            int row  = qr.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCourseStatus(Course course) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course SET STATUS = ? ,update_time = ? WHERE id = ?";

            Object[] param = {course.getStatus(),course.getUpdate_time(),course.getId()};

            int row = qr.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
