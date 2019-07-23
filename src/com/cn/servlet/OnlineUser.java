package com.cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.entity.User;
@WebServlet({"/online"})
public class OnlineUser extends HttpServlet {
	String color[] =new String[]{"red","black"};
	private int i=0;
	private int number=0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();
		Map<User, Boolean> map=(Map<User, Boolean>) this.getServletContext().getAttribute("map");
		
		
				
			 Iterator<Map.Entry<User, Boolean>> it = map.entrySet().iterator();  
				while(it.hasNext()){
					Map.Entry<User, Boolean> entry = it.next(); 
					if(entry.getValue() == false){
						it.remove();
						System.out.println("扫到一个被挤下线的..."+entry.getKey().getUserPassword());
						req.getSession().setAttribute("currentUser", null);					
						
					}
					//System.out.println("进来扫描。。。");
				}
				
			if(req.getSession().getAttribute("currentUser")==null){
				System.out.println("这个人被挤下去了,但是他还没有关闭页面。。。");
				if(i>1)i=0;
				System.out.println(color[i]);
				out.println("<h1 style='color:"+color[i++]+";'>您的账号已在别处登录，您已被迫下线，如果非你本人操作，请尽快修改您的密码！</h1>");
			}else{
					User user = (User) req.getSession().getAttribute("currentUser");
					
					out.println("<table border='1' width='200' heigth='200' style=\"color:#C1FFC1; margin-top:10px;text-align:center; font-size:20px;\"> " );
					out.println("<tr ><th >用户</th></tr>");
					for(Map.Entry<User, Boolean> entry :map.entrySet()){
						if(entry.getKey().getUserName().equals(user.getUserName())){
							out.println("<tr><td style='color:red;'>"+entry.getKey().getUserName()+"(自己)</td></tr>");
						}else{
							out.println("<tr><td>"+entry.getKey().getUserName()+"</td></tr>");
						}
						
					}
				
					out.println("</table>");
				
				
			
			}
			
			 /*Cookie[] cookies = req.getCookies();
		        if(cookies !=null){
		            for(int i = 0;i < cookies.length;i++){  //遍历cookie对象集合
		            	if(cookies[i].getName().equals("JSESSIONID")){
		            		req.getSession().getId()
		                	}
		            	}
		            }*/
			out.close();
		
			return;
			
		
			
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
