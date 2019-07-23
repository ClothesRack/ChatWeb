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
			//��Ϣ����
			List<String > talksList =  (List<String>) this.getServletContext().getAttribute("talksList");
			 if(talksList.size()>=10)talksList.remove(0);
			 talksList.add(talk);
			 
			 
			//�洢��Ϣ
			this.getServletContext().setAttribute("talk", talk);
			//�õ���ǰ�û�
			User user = (User) req.getSession().getAttribute("currentUser");
			
			
			//��ÿ���û������Խ�����Ϣ��
			 for(Map.Entry<User, Boolean> entry : map.entrySet()){
				 	//���ý����� ֱ������Ϣ��������
				 //entry.getKey().setAccep(true);
				 //���ý��ܵ���Ϣ����
				 if(entry.getKey()!=user){
					  num =  entry.getKey().getNumMessage();
					  entry.getKey().setNumMessage(num+1);				
					 System.out.println(entry.getKey().getUserName()+"���"+entry.getKey().getNumMessage()+"����Ϣ");
				 }
				 
			 }
			
			 //�Լ�˵��Ȼ���Լ����Լ�״̬�ر���
			 //user.setAccep(false);
			 //����current�û���״̬
			req.getSession().setAttribute("currentUser", user);
	}
}
