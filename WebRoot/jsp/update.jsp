<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <meta charset="utf-8"/> 
    <title>更新您的信息</title>

  </head>
  
  <body>
  <%	
  String name = (String)request.getParameter("userName");
  	name = new String(name.getBytes("iso8859-1"),"utf-8");
  	if(!name.equals("admin")){
  	response.getWriter().write("<script>alert('您不是超级管理员，无法更新信息');window.location.href='/chat/jsp/main.jsp';</script>");
  	return;
  	}
  	
  	%>
  	<h1>您好,<%=name %> ,请选择更新您的密码或年龄</h1>
    <div style="width: 250px; margin:0px auto">
	<form action="updateUserInfo" method="POST">
		<input type="text"  name ="userName" value="<%=name%>"hidden="hidden">
    	密码：<input type="password" id="pwd" name ="userPassword" style="float:right"><br><br> 
    	年龄：<select style="margin-left:50px;" name="age">
  				  <option value ="17">17</option>
				  <option value ="18">18</option>
				  <option value="19">19</option>
				  <option value="20">20</option>
				  <option value="21">21</option>
				  <option value="22">22</option>
				  <option value="23">23</option>
				  <option value="24">24</option>
			</select><br/><br/><br/>
			<input type="submit" value="更新" style="margin-left:100px;" >
			</form>
			  
	</div>
  </body>
</html>
