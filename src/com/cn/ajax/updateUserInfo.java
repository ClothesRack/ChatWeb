package com.cn.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dao.UserDAO;
import com.cn.entity.User;
@WebServlet({"/updateUserInfo"})
public class updateUserInfo extends HttpServlet {
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String name = (String ) req.getParameter("userName");
			Integer age = Integer.parseInt(req.getParameter("age"));
			String userPassword = (String ) req.getParameter("userPassword");
			UserDAO userDAO = new UserDAO();
			boolean flag =userDAO.update(name,age,userPassword);
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			if(flag){
				out.write("<html><head><meta charset='utf-8'></head><body><h1>修改成功！<a href='/servletTest/jsp/main.jsp'>点此返回</a></h1></body></html>");
			}else{
				out.write("<html><head><meta charset='utf-8'></head><body><h1>修改失败！</h1></body></html>");
			}
			}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doPost(req, resp);
	}
}
