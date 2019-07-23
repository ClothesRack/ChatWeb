package com.cn.listen;

import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cn.entity.User;

public class OnlineUserSession implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent sec) {
		System.out.println("ҳ�汻���µ�session����.......");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sec) {
		System.out.println("�����˳���.......................");
		User user = (User)sec.getSession().getAttribute("currentUser");
		if(user != null){
			sec.getSession().setAttribute("currentUser", null);
			Map<User, Boolean> map = (Map<User, Boolean>) sec.getSession().getServletContext().getAttribute("map");
			map.remove(user);
		}
		
	}
	
}
