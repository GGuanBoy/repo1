<%--
  Created by IntelliJ IDEA.
  User: guan
  Date: 2021/3/19
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    文件上传三要素
        1. post提交
        2.表单 enctype属性 multipart/form-data
        3.表单中必须有文件上传项
--%>
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">
        <input type="file" name="upload"> <br>
        <input type="text" name="name"><br>
        <input type="text" name="password"><br>
        <input type="submit" value="文件上传">
    </form>

    <img src="/upload/e3cccc6f2fb54376b28a2e4697643923_4133a15e2f9ed19110cc891eaaf66711_t.gif">
</body>
</html>
