package com.cn.servlet;


import java.io.IOException;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cn.dao.UserDAO;
import com.cn.entity.ThreadFlag;
import com.cn.entity.User;

@WebServlet({"/LoginServlet"})
public class LoginServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private UserDAO userDAO = new UserDAO();
  private List<User> list;
  Map<User, Boolean> map;
  boolean online = true;
  private List<String> talksList = new ArrayList<String>();
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	  //初始化下
	 online = true;
    request.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");
    String userPassword = request.getParameter("userPassword");
    String isSaveState = request.getParameter("isSaveState");
    Cookie cookie = null;
    Cookie cookie2 = null;
    SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
    String dateTime = dateFm.format(new Date());

    User user = this.userDAO.loginUser(userName, userPassword);
    //如果用户注册了 那么list不为空 可以获取，如果为空，那么直接读数据库
    if(list==null){
    	list = (List<User>) request.getSession().getAttribute("list");
    }
    if(list==null)
    list = userDAO.list();
    if (user != null)
    {
      if ("on".equals(isSaveState)) {
        cookie = new Cookie("userName", URLEncoder.encode(user.getUserName(), "UTF-8"));
        cookie2 = new Cookie("userPassword", URLEncoder.encode(user.getUserPassword(), "UTF-8"));

        cookie.setMaxAge(3600);
        cookie2.setMaxAge(3600);

        response.addCookie(cookie);
        response.addCookie(cookie2);
      }
      else {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
          for (int i = 0; i < cookies.length; i++)
          {
            if (cookies[i].getName().equals("userName"))
            {
              Cookie cookie3 = new Cookie("userName", "");
              cookie3.setPath("/chat");
              cookie3.setMaxAge(0);
              response.addCookie(cookie3);
            }
            if (!cookies[i].getName().equals("userPassword"))
              continue;
            Cookie cookie3 = new Cookie("userPassword", "");
            cookie3.setPath("/chat");
            cookie3.setMaxAge(0);
            response.addCookie(cookie3);
          }
        }

      }
      //这里给session域存储了list 可以在main.jsp获取list对象
      request.getSession().setAttribute("list", list);
   
      System.out.println("用户：" + userName + "，成功登录了....." + dateTime);
      map = (Map<User, Boolean>) this.getServletContext().getAttribute("map");
     for(Map.Entry<User, Boolean> entry : map.entrySet()){
    	 if(entry.getKey().getUserName().equals(userName)){
    		 //如果集合有这个用户，则把online赋值false 等会会删除它
    		 online = false;
    		 map.put(entry.getKey(), online);
    		 break;
    	 }
     }
     	//增加用户
       	 map.put(user, true);
       	
       	request.getServletContext().setAttribute("loginuserName",user.getUserName());
       	//上线一个人 就要通知所有人 有人上来了 
       	for(Map.Entry<User, Boolean> entry : map.entrySet()){
       		entry.getKey().setAddu(true);
       	}
       	
       
       	 request.getSession().setAttribute("currentUser", user);
       	getServletContext().setAttribute("talksList", talksList);
       	 response.sendRedirect("/chat/jsp/main.jsp");
     	
    /* if(!online){
    	 PrintWriter printWriter = response.getWriter();
         response.setContentType("text/html;charset=UTF-8");
         printWriter.write("<script>alert('抱歉，当前账号已经登录！请先退出或者换个账号试试');location.href='/servletTest/login.jsp';</script>");
         printWriter.close();
     }else{
    	 map.put(user, online);
    	 request.getSession().setAttribute("currentUser", user);
    	 response.sendRedirect("/servletTest/jsp/main.jsp");
    	 
     }*/

     
    }
    else {
      System.out.println("用户：" + userName + "尝试登录但失败了....~~" + dateTime);
      PrintWriter printWriter = response.getWriter();
      response.setContentType("text/html;charset=UTF-8");
      printWriter.write("<script>alert('帐号或者密码错误，登录失败！如果你还没有帐号，请先注册一个吧~');location.href='/chat/login.jsp';</script>");
      printWriter.close();
    }
  }
  @Override
	public void init() throws ServletException {
	  System.out.println("login Servlet初始化.....");
	  	
		new Thread(new ThreadFlag(getServletContext())).start();
	}
}
