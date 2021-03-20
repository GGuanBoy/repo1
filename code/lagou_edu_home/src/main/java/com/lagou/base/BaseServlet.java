package com.lagou.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    /*
     doGet方法作为一个调度器，根据请求功能的不同，调用对应的方法
       规定必须传递一个参数
           methodName - 功能名
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数，要访问的方法名
        String methodName;
        //1.1获取POST请求的Content-Type类型
        String contentType = request.getHeader("Content-Type");
        //1.2如果是json类型，取得并封装到map
        if ("application/json;charset=utf-8".equals(contentType)){
            String postJSON = getPostJSON(request);
            Map<String,Object> map = JSON.parseObject(postJSON, Map.class);
            methodName = (String)map.get("methodName");
            request.setAttribute("map",map);
        }else {
            methodName = request.getParameter("methodName");
        }

        //2.判断，执行对应的方法
        if (methodName!=null){
            Class c = this.getClass();
            try {
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this,request,response);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String getPostJSON(HttpServletRequest request){
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
