package com.lagou.web.servlet;

import com.lagou.base.Constants;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;
import com.lagou.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/courseSalesInfo")
public class CourseSalesInfoServlet extends HttpServlet {
    /*
        保存课程营销信息
            收集单表数据，封装的course对象中，将图片上传到tomcat服务器中
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.创建Course对象
            Course course = new Course();
            //2.创建Map集合，用来收集数据
            Map<String,Object> map = new HashMap<>();
            //3.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //4.文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //5.解析request对象，获取表单项集合
            List<FileItem> list = fileUpload.parseRequest(request);
            //6.遍历集合 判断那些是普通的表单项，那些是文件表单项
            for (FileItem item:list){
                boolean formField = item.isFormField();
                if (formField){
                    //是普通表单项
                    String fieldName = item.getFieldName();
                    String value = item.getString("utf-8");
                    map.put(fieldName,value);
                }else {
                    //文件上传项
                    //获取文件名
                    String name = item.getName();
                    String newFileName = UUIDUtils.getUUID() + "_" + name;
                    //获取输入流
                    InputStream is = item.getInputStream();
                    //获取webapps的目录路径
                    String realPath = this.getServletContext().getRealPath("/");
                    String webappsPath = realPath.substring(0, realPath.indexOf("lagou_edu_home"));
                    //创建输出流
                    FileOutputStream fos = new FileOutputStream(webappsPath + "/upload/" + newFileName);
                    IOUtils.copy(is,fos);
                    fos.close();
                    is.close();

                    //将图片路径保存
                    map.put("course_img_url", Constants.LOCAL_URL+"/upload/"+newFileName);
                }
            }
            BeanUtils.populate(course,map);
            //补全课程信息
            String dateFormart = DateUtils.getDateFormart();
            CourseService cs = new CourseServiceImpl();
            if (map.get("id")!=null){
                course.setUpdate_time(dateFormart); //修改时间
                String result = cs.updateCourseSalesInfo(course);
                response.getWriter().print(result);
            }else {
                course.setCreate_time(dateFormart);
                course.setUpdate_time(dateFormart);
                course.setStatus(0);
                String result = cs.saveCourseSalesInfo(course);
                response.getWriter().print(result);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
