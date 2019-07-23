package com.cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.entity.User;
@WebServlet({"/chat"})
public class ChatServlet extends HttpServlet {
	private String talk;
	int num = 0;
	@Override
	protected  synchronized void  doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			 talk = (String)req.getParameter("talk");
		
			 Map<User, Boolean> map = (Map<User, Boolean>) this.getServletContext().getAttribute("map");
			//消息队列
			List<String > talksList =  (List<String>) this.getServletContext().getAttribute("talksList");
			 if(talksList.size()>=10)talksList.remove(0);
			 talksList.add(talk);
			 
			 
			//存储消息
			this.getServletContext().setAttribute("talk", talk);
			//得到当前用户
			User user = (User) req.getSession().getAttribute("currentUser");
			
			
			//让每个用户都可以接受信息了
			 for(Map.Entry<User, Boolean> entry : map.entrySet()){
				 	//不用接收了 直接拿消息数量决定
				 //entry.getKey().setAccep(true);
				 //设置接受的消息个数
				 if(entry.getKey()!=user){
					  num =  entry.getKey().getNumMessage();
					  entry.getKey().setNumMessage(num+1);				
					 System.out.println(entry.getKey().getUserName()+"获得"+entry.getKey().getNumMessage()+"条消息");
				 }
				 
			 }
			
			 //自己说的然后自己把自己状态关闭了
			 //user.setAccep(false);
			 //更新current用户的状态
			req.getSession().setAttribute("currentUser", user);
	}
}
