<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
  	<style type="text/css">
	span{
	color:red;
	}
	</style>
    <base href="<%=basePath%>">
    
    <title>注册新账号</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<div style="width: 500px; margin:0px auto">
	<h1 style="color:blue;">欢迎来到注册界面~</h1>
	<form action="adduser" method="POST" onsubmit=" return judge(this)">
	<span >用户名：&nbsp&nbsp</span><input type="text" id="uname" name="userName" ><br/> <br/>  
    	<span>密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码：</span><input type="password" id="pwd" name ="userPassword" onBlur="loseFocus()" onFocus="getFocus()"><br/><br/>
    	<span >确认密码：</span><input type="password" id="pwd2" name ="userPassword2" onBlur="loseFocus()" onFocus="getFocus()">
    	<span id='alike' style="color:red;"></span>
    	<br/><br/> 
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
			<input type="submit" value="注册" style="margin-left:50px;" id='submitto' />
			<input type="button" value="返回" onclick="goback()"style="margin-left:50px;"/>
			</form>
			  
	</div>

		
  </body>
  <script>
  		

  	var submit = document.getElementById('submitto');
  	function goback(){
  		location.href ="/chat/login.jsp";
  	}
  	function judge(form){
  		var name = form.userName.value;
  		var password = form.userPassword.value;
  		var password2 = form.userPassword2.value;
  		var alike = form.alike;
  		if(name == ""){
			alert("用户名为空");
			return false;
		}
		if(password ==""){
			alert("密码为空");
			return false;
		}
  	}
  		function loseFocus(){
  		  	var password =document.getElementById('pwd').value;
  			var password2 = document.getElementById('pwd2').value;
  			
  			if(password != password2){
  				alike.innerHTML = "两次密码不一致";
  				submit.disabled = true;
  		}
  	}
  	  	function getFocus(){
  	  		var alike = document.getElementById('alike');
  			alike.innerHTML = "";
  			submit.disabled = false;
  		
  	}
  	
  </script>
</html>
