<%@page import="com.cn.dao.UserDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--  使用jstl标签 必须加这句代码-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
   		List list = new UserDAO().list();
   		request.getSession().setAttribute("list", list);
  	 %>
  <head>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UserAll.jsp' starting page</title>
    <style type="text/css">
    	a{
    		text-decoration:none;
    		font-size:20px;
    		}
    		/* 已访问的链接 */
    		a:visited {
    		color:#F0FFFF;
    		}
    		/* //未访问的链接 */
    		a:link{
    			color:#F0FFFF;
    		}
    		/* //鼠标在链接上 */
    		a:hover{
    			color:rgb(87,250,255);
    		}
    		/* //鼠标点击时 */
    		a:active{
    		color:red;
    		}
   </style>
	

  </head>
  
  <body style="background-image:url(/chat/img/5.jpg); background-attachment:fixed;background-size:100% 100%">
   <div style="width:500px;margin:0px auto;">
    <a style="margin-left:200px;" href="/chat/jsp/main.jsp" >返回聊天室</a>
    <table id='dormperson' border="1" cellpadding="20" cellspacing="1" style='border-color: rgb(87,250,255);color:#F0FFFF;border-style: solid; border-width: 5px 5px 5px 5px;'>
		   	<tr>
		   		<th>编号</th>
		   		<th>姓名</th>
		   		<th>密码</th>
		   		<th>年龄</th>
		   		<th>操作</th>
		   	</tr>
		   	<c:forEach var="user"  items="${list}" >
			   	<tr id=${user.id }>
			   		<td>${user.id }</td>
			   		<td>${user.userName }</td>
			   		<td>${user.userPassword}</td>
			   		<td>${user.age }</td>
			   		<td><a href="/chat/jsp/update.jsp?userName=${user.userName}">更新</a>|<a href ="javaScript:void(0);" onclick="loadXMLDoc('${user.id}')">删除</a></td>
			   	</tr>
		   	</c:forEach>

   		</table>
   		</div>
  </body>
  <script type="text/javascript">
  			
   function loadXMLDoc( id ){
   var dormperson = document.getElementById("dormperson"); 
	var idelem = document.getElementById(id);
  if (window.XMLHttpRequest)
	{
		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		// IE6, IE5 浏览器执行代码
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		 if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
				//这个不行的原因是idelem的父亲元素是tbody
				//dormperson.removeChild(idelem);	
				var message = xmlhttp.responseText;
				if(message=="登录状态"){
					alert("当前账号用户在线，删除失败！");
					return;
				}
				if(message=="非超级管理员"){
					alert("您不是超级管理员，没有删除权限，删除失败！");
					return;
				}
				if(message!="掉线"){
					alert(message);
					idelem.parentNode.removeChild(idelem);
				}else{
					alert("您已掉线，删除失败!");
					return;
				}
				
		} 
	}
	xmlhttp.open("GET","/chat/deletebyid?id="+id+"",true);
	xmlhttp.send(null);

}
  </script>
</html>
