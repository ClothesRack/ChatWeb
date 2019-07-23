<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
     <%
    	if(request.getSession().getAttribute("currentUser")!=null){
				response.sendRedirect("/chat/jsp/main.jsp"); 	
    	}
    	
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
		<style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
			}
			body{
				width=1500px;
				height: 1500px;
				background-color: #4D7190;
				background-image: url(img/background.png);
				background-repeat: no-repeat;
			}
			.logo{
				width: 341px;
				height: 348px;
				margin:0 auto;
				background-image: url(img/logo.png);
				background-repeat: no-repeat;
				
			}
			.user{
				width: 800px;
				height: 800px;
				margin: 0px auto;
				
			}
			input[type="password"],[type="text"]{
				width: 519px;
				height: 101px;
				font-size: 45px;
				font-weight: 600;
				color:deepskyblue;
				/*透明背景*/
				background:none;
				background-image: url(img/logn.png);
				background-repeat: no-repeat;
				margin: 30px 100px;
				padding-left: 70px;
				letter-spacing: 5px;
				/*去掉边框*/
				border:0;
				outline:none;	
				
			}
			input[type="password"]{
				letter-spacing: 10px;
				color: red;
				margin: 30px 0px 30px 100px;
			}
		
			.user>a{
				display: block;
				width: 519px;
				height: 95px;
				background-image: url(img/denglu.png);
				margin: 30px 100px;			
				/*去除下滑线*/
				text-decoration: none;
				color: #FFFFFF;
				font-weight: bold;
				
			}
			.user>a:hover{
				color:mediumpurple;
				transition: 1s;
			}
			.user>a:active{
				color:lightblue;
			}
			.user>a:before{
				content: "";
				display: table;
			}
			.user>a>p{
					font-size: 35px;
					letter-spacing: 5px;
					text-align: center;
					margin: 25px auto;
					
			}
		</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>登录页面</title>  
</head>  
<body style="background-image:url(img/1.jpg); background-attachment:fixed;background-size:cover;"> 
<%
        Cookie[] cookies = request.getCookies();
        String username = "";    //登录用户
        String password = "";    //注册时间
        if(cookies !=null){
            for(int i = 0;i < cookies.length;i++){  //遍历cookie对象集合
            if(cookies[i].getName().equals("userName")){
                username = cookies[i].getValue();
                }
             if(cookies[i].getName().equals("userPassword")){
                   password = cookies[i].getValue();
                }
             
              
                
            }
        }
    
     %>    
<div class="logo">
		</div>
		<form action="LoginServlet" method="post" onsubmit=" return judge(this)" name='form1' id='form1'>
		<div class="user">
		<label for="uname" style="font-size:25px;color:mediumpurple;">用户名：</label></br>
			<input type="text" name="userName" id="uname" value="<%=username %>" maxlength="12" /></br>
		<label for="psd" style="font-size:25px;color:mediumpurple;">密  码：</label></br>
			<input type="password" name="userPassword" id="psd" value="<%=password %>" maxlength="12"/>
			 <input type ="checkbox" name = "isSaveState" style="clear:both;"/><span style="color:mediumpurple;">记住密码</span></br>
			<a href='javascript:document.form1.submit();'><p>Login</p></a>  	
			<a href="/chat/adduser.jsp"><p>SignIn</p></a>
			
			
		</div>
 		</form>

</body> 
<script type="text/javascript">
 var fom = document.getElementById('form1');
 var username = document.getElementById('uname');
 	username.focus();
	function judge(form){
	//元素的name属性
	var name = form.userName.value;
	var password = form.userPassword.value;
	
	if(name == ""){
		alert("用户名为空");
		return false;
	}
	if(password ==""){
		alert("密码为空");
		return false;
	}
	}
		document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];   
             if(e && e.keyCode==13){ // enter 键
         
                fom.submit();
            }
        }; 
 
</script>
</html>