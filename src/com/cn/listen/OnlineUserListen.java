package com.cn.listen;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cn.entity.User;

public class OnlineUserListen implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext sc= sce.getServletContext();
		Object c = sc.getAttribute("map");
		if(c!=null){
			sc.removeAttribute("map");
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("用户在线集合已创建.......");
		Map<User,Boolean> map = new HashMap<User, Boolean>();
		sce.getServletContext().setAttribute("map", map);
	}

}
