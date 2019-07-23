<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'succu.jsp' starting page</title>
    <meta charset="utf-8"/>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 <% 
  	//name =java.net.URLEncoder.encode(name,"iso-8859-1");//编码
  	//name=java.net.URLDecoder.decode(name,"utf-8"); //解码
 if(request.getParameter("name")==null)
 	response.sendRedirect("login.jsp");
 	response.setCharacterEncoding("utf-8");
 	String name = request.getParameter("name");
 
 %>
  您好，<%=name%>：
   <p>您已经成功注册，请点击登录吧~......<a href="login.jsp">点此登录</a></p> <br>
  </body>
</html>
