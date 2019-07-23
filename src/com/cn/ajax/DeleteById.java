package com.cn.ajax;


import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cn.dao.UserDAO;
import com.cn.entity.User;

public class DeleteById extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	  User curruser;
	  String ids = request.getParameter("id");
	  if(ids!=null){
		  int id = Integer.parseInt(ids.trim());
		    Integer integerId = new Integer(id);
			response.setCharacterEncoding("utf-8");
	    	PrintWriter out = response.getWriter();
		  if(( curruser= (User)request.getSession().getAttribute("currentUser"))!=null){
			  Map<User, Boolean> map = (Map<User, Boolean>) this.getServletContext().getAttribute("map");
			  for(Map.Entry<User, Boolean> entry :map.entrySet()){
				   //���Լ������
					if(entry.getKey().getId()==id){
						out.print("��¼״̬");
						return;
					}
					if(!entry.getKey().getUserName().equals("admin")){
						out.print("�ǳ�������Ա");
						return;
					}
					
				}
			    if(curruser.getId()!=integerId){
			    	 UserDAO userDAO = new UserDAO();
					    userDAO.deleteUserById(integerId);
					    SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
					    String dateTime = dateFm.format(new Date());
					    List<User> list = (List)request.getSession().getAttribute("list");
					    for (User user : list)
					      if (user.getId() == id) {
					        System.out.println("�û�����" + user.getUserName() + ":" + "��ɾ����..." + dateTime);
					        list.remove(user);
					        break;
					      }
			    }
			   
		  }else{
			 
		    
		    	out.print("����");
		  }
		 
		  out.close();
	  }else{
		  response.getWriter().write("<script>alert('��Ҫ����~');window.location.href='/servletTest/jsp/main.jsp';</script>");
	  }
	   
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doGet(request, response);
  }
}