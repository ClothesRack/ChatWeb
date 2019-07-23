package com.cn.servlet;


import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PUT;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.cn.dao.UserDAO;
import com.cn.entity.User;

public class AddUser extends HttpServlet
{
  private UserDAO userDAO = new UserDAO();
  private List list ;
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    /*response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    out.println("<HTML>");
    out.println("  <HEAD><TITLE>A Servlet</TITLE><meta charset='UTF-8'/></HEAD>");
    out.println("  <BODY>");
    out.println("<script>alert('�Ƿ����ʣ���');</script>");
    out.println("<p>����һ�ֲ���ȫ�ķ�ʽ��������ص�¼ҳ��...<a href='login.jsp'>����</a></p>");
    out.println("  </BODY>");
    out.println("</HTML>");
    out.flush();
    out.close();*/
	  response.sendRedirect("/chat/404.html");
  }

  
public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	
    SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
    String dateTime = dateFm.format(new Date());

    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-type", "text/html;charset=UTF-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String name = request.getParameter("userName");
    String password = request.getParameter("userPassword");
    String age = request.getParameter("age");
    User newuser = new User(name, password, Integer.parseInt(age));
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    out.println("<HTML>");
    out.println("  <HEAD><TITLE>A Servlet</TITLE><meta charset='utf-8'/></HEAD>");
    out.println("  <BODY>");
    boolean flag = true;
  
	list= userDAO.list();
      lookforhas(request, response, list, dateTime, out, name, newuser, 
        flag);

    out.println("  </BODY>");
    out.println("</HTML>");
    out.flush();
    out.close();
  }

  public void lookforhas(HttpServletRequest request, HttpServletResponse response, List<User> list, String dateTime, PrintWriter out, String name, User newuser, boolean flag)
    throws IOException
  {
    Iterator it = list.iterator();
    while (it.hasNext()) {
      User user = (User)it.next();
      if (user.getUserName().equals(name)) {
        out.print("ע��ʧ����...reason:" + name + "�û����Ѵ���");
        out.print("<a href=\"/chat/adduser.jsp\">����ע�����</a>");
        flag = false;
        break;
      }
    }
    if (flag) {
      //���������������
      this.userDAO.registerUser(newuser);
      list.add(newuser);
      request.getSession().setAttribute("list", list);
     
      //name��UTF-8 ��ʽ ��ת����iso-8859-1��ʽ����  ���Ա������±���һ��
      name = new String (name.getBytes("utf-8"),"iso-8859-1");
      //out.println("<html><head><meta charset='utf-8'></head><body><h1>"+newuser.getUserName() +",����ע��ɹ���<a href='/servletTest/login.jsp'>��˷���</a></h1></body></html>");
      
      response.sendRedirect("/chat/succu.jsp?name="+name );
      System.out.println("�û�����" + newuser.getUserName() + ":" + "�ɹ�ע��...." + dateTime);
    }
  }
  
}