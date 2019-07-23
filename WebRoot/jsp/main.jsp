
<%@page import="com.cn.dao.UserDAO,com.cn.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

   
<!--JSTL核心表情控主要包括流程处理标签,如果用到<c:forEach>等,需要在lib下导入jstl-1.2.jar包  -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <title>欢迎您，${currentUser.userName}</title>
    <link rel="stylesheet" type="text/css" href="buttons.css"/>
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
    	   *{padding:0px;margin:0px;font-family:"微软雅黑";}
    dd,dl,dt,em,i,p,textarea,a,div{padding:0px;margin:0px;}
    img{border:0px;}
    ul,li{padding:0px;margin:0px;list-style:none; float:left}
    .fl{ float:left}
    .fr{ float:right}
    
    /*右下角弹出*/
    .dingwe{ position:relative;}
    .tipfloat{display:none;z-index:999;border:1px #8e9cde solid; position:absolute; bottom:0px; right:17px;width:288px;height:268px; background:#fff}
    .tipfloat_bt{ height:49px; line-height:49px;background:#8e9cde; padding:0px 20px; font-size:18px; color:#fff; }
   </style>
  </head>
  
  <body style="background-image:url(/chat/img/5.jpg); background-attachment:fixed;background-size:100% 100%"  >
	<p id='currentUser' hidden="hidden">${currentUser.userName}</p>
   <div style="width:500px;margin:0px auto;">
 <marquee  scrollAmount=2 direction="left" scrolldelay="60" style="color:rgb(87,250,255);font-size:20px;">${currentUser.userName}，欢迎你访问本页面~</marquee> 
   <a href="/chat/LoginOut" style="margin-left:200px;">安全退出|</a>
    <a href="/chat/jsp/UserAll.jsp" >后台页面</a>

   		
   </div>
   <div style="width:800px; margin:80px auto;">
	   <div   style="width:25%;float:left;">
	   		<marquee scrollAmount=2 direction="left" scrolldelay="60" style="color:rgb(87,250,255);"><h1>在线用户列表</h1></marquee>
	   		<div id="online"></div>
	   		<%-- <h1>在线用户列表</h1>
	   		<c:forEach items="${map}" var="m">
	    		姓名---->${m.key.userName}--->>${m.value }<br/>
			</c:forEach> --%>
	   </div >
   	<div style="width:70%; float:right">
   	<textarea rows="20" id='say' cols="50" style=" border:0; color:#E0FFFF;background-color:transparent;font-size:22px; " readonly="readonly">${sayinnerHTML }</textarea>
   	<input type="text" id='word' style="width:400px;font-size:25px;margin-right:80px"/>
	<button  onclick="send(currentUser)" id='send'class="button button-3d button-box button-jumbo"><i class="fa fa-thumbs-up"></i><span id='s'style="margin-top:10px;">send</span></button>
   <!-- 	<input type="button" id='send' value="发送" onclick="send()"/> -->
   	</div>
   </div>
   <audio id="bgMusic">
    <source  src="/chat/jsp/83766.mp3" type="audio/mp3">
    
</audio>
<div style="background:#CCC">
     <!--弹出信息 右下角-->
    <div class="tipfloat" data-num="3">
        <p class="tipfloat_bt">
            <span class="fl">消息</span>
            <span class="fr close"><img src="/chat/images/guanbi.png"></span>
        </p>
        <div class="ranklist">
             <div class="xx_nrong">
                
            </div> 
        </div>
    </div>
</div>
  </body>
  <script type="text/javascript" src="/chat/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript">
  <%
	Map<User,Boolean> map = (Map<User,Boolean>)request.getServletContext().getAttribute("map");
	User user = (User)request.getSession().getAttribute("currentUser");
	if(!map.containsKey(user)){
		user = null;
		request.getSession().setAttribute("currentUser",user);	
		request.getSession().setAttribute("sayinnerHTML",null);		
		
 %>
 	
 	location.href="/chat/login.jsp"
 	<%}%>
  var xmlhttp;
  var senButt= document.getElementById("send");
  var s= document.getElementById("s");
  var currentUser= document.getElementById("currentUser");
  var i=3,v=1;
  var getchat;
  var say = document.getElementById("say"); 
  var word = document.getElementById("word"); 
  var vid = document.getElementById("bgMusic");//获取音频对象  
  //say.innHTML+="系统：您已进入聊天室.....(请文明用语，谢谢配合)\n"; 
  vid.addEventListener("ended",function() {  
  				    var start = 0;//定义循环的变量  
			        var times=1;//定于循环的次数  
			        	//播放完出发 事件
			        vid.play();//启动音频，也就是播放  
			        start++;//循环  
			        start == times && vid.pause();//也就是当循环的变量等于次数的时候，就会终止循环并且关掉音频  
			        });  
 document.addEventListener('touchstart', function(){ 
 		if(v==1){
	 		vid.play();	
	 		v = 0;
 		}	
		}, false);
  window.onload = function(){
 	var online = document.getElementById("online");
 	 
 	 var geton =setInterval(function(){
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
			
				online.innerHTML = xmlhttp.responseText;
				
				if(online.innerHTML.indexOf("被迫下线")!=-1){
					alert("您的账号被挤下线了...若非你本人操作，请尽快修改密码！");
					location.href="/chat/login.jsp"
					senButt.disabled="disabled";
					//为什么不关这个定时器 因为要提醒（字体闪）
					//clearInterval(geton); 
					clearInterval(getchat); 
					
					
			}
			
			}
		} 
	
	xmlhttp.open("POST","/chat/online",true);
    xmlhttp.send(null);
    $(".tipfloat").animate({height:"hide"},800);
 	 },3000);
 	 
 	 getchat = setInterval(function(){
 	 if (window.XMLHttpRequest)
	{
		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xm=new XMLHttpRequest();
	}
	else
	{
		// IE6, IE5 浏览器执行代码
		xm=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xm.onreadystatechange=function()
	{
		 if (xm.readyState==4 && xm.status==200)
		{
			if(xm.responseText=="服务器刚刚重新启动了，请您重新登录谢谢"){
				say.innerHTML += xm.responseText;
			    senButt.disabled="disabled";
			    clearInterval(geton); 
				clearInterval(getchat);
				return;
				
			}
			//获取聊天
			say.innerHTML += xm.responseText;
			if(xm.responseText!=""){   
			        vid.play();//启动音频，用于第一次启动  
			       	say.scrollTop = say.scrollHeight ;
					word.focus();
					$(".xx_nrong").html("你收到新消息啦~");
       				 if(xm.responseText.indexOf(",已进入群聊")!=-1){
       				  	lala = xm.responseText.split(",")
       				   	bhname = lala[0].substr(46);
       				 	$(".xx_nrong").html(bhname+"上线啦~");
       				 }
       				  if(xm.responseText.indexOf(",退出了群聊")!=-1){
       				  	lala = xm.responseText.split(",")
       				   	bhname = lala[0].substr(46);
       				 	$(".xx_nrong").html(bhname+"下线了~");
       				 }
					 $(".tipfloat").animate({height:"show"},800);	
						
					
			}

		} 
	}
	xm.open("POST","/chat/saychat",true);
	//在使用原生AJAX POST请求时，如果需要像 HTML 表单那样 POST 数据,需要明确设置Request Header	
	xm.setRequestHeader("content-type","application/x-www-form-urlencoded");
    xm.send('sayinnerHTML='+say.innerHTML);

	
	if(senButt.disabled==true){
		if(i<=0){
			senButt.disabled=false;
			s.innerHTML="send";
			i=3;
		
		}else{
			s.innerHTML=" "+i--+" ";
		}
		
 	 	
	}
	},1000);
 	
 	
  }
  	
 
	function send(){
			var name =currentUser.innerHTML;
		  var date=new Date();
		   var year=date.getFullYear(); //获取当前年份
		   var mon=date.getMonth()+1; //获取当前月份
		   var da=date.getDate(); //获取当前日
		   var day=date.getDay(); //获取当前星期几
		   var h=date.getHours(); //获取小时
		   var m=date.getMinutes(); //获取分钟
		   var s=date.getSeconds(); //获取秒
		   if (window.XMLHttpRequest)
			{
				//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
				xmlh=new XMLHttpRequest();
			}
			else
			{
				// IE6, IE5 浏览器执行代码
				xmlh=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlh.onreadystatechange=function()
			{
				 if (xmlh.readyState==4 && xmlh.status==200)
				{
					
					
				} 
			}

		if(word.value==""){
			alert("您还未输入要发送的信息!");
			return;
		}
		//百分号换成%25
		var postStr = word.value.replace(/%/g, "%25");  
		var talk = name+"  " +h+":"+m+":"+s+"  说：\n   "+postStr+"\n";
		var talk2 = name+"  " +h+":"+m+":"+s+"  说：\n   "+word.value+"\n";
		word.value="";
		//文本框获取焦点
		word.focus();
		say.innerHTML += talk2;
		say.scrollTop = say.scrollHeight ;
		xmlh.open("POST","/chat/chat",true);
		 xmlh.setRequestHeader("content-type","application/x-www-form-urlencoded");
         xmlh.send('talk='+talk);
         senButt.disabled="disabled";
	} 
	
	document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];   
             if(e && e.keyCode==13){ // enter 键
             if(senButt.disabled!=true)
                 //要做的事情
                 send();
            }
        }; 


var tk_index=0;
var xx_num;
$(function(){
   

    $(".close").click(function(){
        $(".tipfloat").animate({height:"hide"},800);

    });
})
 window.onbeforeunload=function(event){
 	
	 event.returnValue = "确定要离开页面吗？";
  	
  	
  }

  </script>


</html>