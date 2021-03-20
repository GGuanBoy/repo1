<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/test?methodName=addCourse" >新建课程</a>
<a href="${pageContext.request.contextPath}/test?methodName=findByName" >查找(根据名称)</a>
<a href="${pageContext.request.contextPath}/test?methodName=findByStatus" >查找(根据状态)</a>
</body>
</html>