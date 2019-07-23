package com.cn.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.entity.User;

public class LoginOut extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    User CurrentUser = (User)request.getSession().getAttribute("currentUser");
    if (CurrentUser != null) {
      String name = CurrentUser.getUserName();
 
      User user = (User)request.getSession().getAttribute("currentUser");
      	//在线集合删掉他！
		if(user != null){
			System.out.println("用户："+user.getUserName()+"，退出了......");
			Map<User, Boolean> map = (Map<User, Boolean>) request.getSession().getServletContext().getAttribute("map");
			for(Map.Entry<User, Boolean> entry : map.entrySet()){
				entry.getKey().setLoninOut(true);
			}
			request.getServletContext().setAttribute("logoutUserName", user.getUserName());
			request.getSession().setAttribute("currentUser", null);
			
			map.remove(user);
			request.getSession().setAttribute("sayinnerHTML", null);
		}
		
 
    
    }

	response.sendRedirect("/chat/login.jsp");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    out.println("<HTML>");
    out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
    out.println("  <BODY>");
    out.print("    This is ");
    out.print(getClass());
    out.println(", using the POST method");
    out.println("  </BODY>");
    out.println("</HTML>");
    out.flush();
    out.close();
  }
  @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("退出servlet执行了初始化.....");
	}
}
