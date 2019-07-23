package com.cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.entity.User;
@WebServlet({"/saychat"})
public class saychat extends HttpServlet {
		
		SimpleDateFormat sFormat = new SimpleDateFormat("hh:mm:ss");
		Random random = new Random();
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			String sb = (String)req.getParameter("sayinnerHTML");
			req.getSession().setAttribute("sayinnerHTML", sb);
		
			String dateTime = sFormat.format(new Date());
			User user = (User) req.getSession().getAttribute("currentUser");
			int flagnum = random.nextInt(1000);
			if(user==null)return;
			//当两个数字一样 循环。。。直到不一样跳出
			while(user.getFlagnum()==flagnum){	
				flagnum = random.nextInt(1000);
				System.out.println("重复设置："+user.getFlagnum());
			}
			//System.out.println("设置："+user.getFlagnum());
			if(user.getFlagnum()==user.getFlagnum2())
					//如果两个数字相同，那么重新给flag个一个值。不能让两个数字相同
			user.setFlagnum(random.nextInt(1000));
			Map<User, Boolean> map = (Map<User, Boolean>) this.getServletContext().getAttribute("map");

			String talk = (String) this.getServletContext().getAttribute("talk");
			
			if(user.isAddu()){
				String adduname = (String) this.getServletContext().getAttribute("loginuserName");
				out.write("------------------------------------------\n系统："+adduname+",已进入群聊"+"  "+dateTime+"\n"+"------------------------------------------\n");
				user.setAddu(false);
			}
			if(user.isLoninOut()){
				String adduname = (String) this.getServletContext().getAttribute("logoutUserName");
				out.write("------------------------------------------\n系统："+adduname+",退出了群聊"+"  "+dateTime+"\n"+"------------------------------------------\n");
				user.setLoninOut(false);
			}
			if(/*!user.getAccep()*/user.getNumMessage()==0){
				if(!map.containsKey(user)){
					out.write("服务器刚刚重新启动了，请您重新登录谢谢");
					user = null;
					req.getSession().setAttribute("currentUser", null);
				}
				return;
			}
			//req.getSession().setAttribute("currentUser", user);
			List<String> talkList = (List<String>) getServletContext().getAttribute("talksList");
			if(talkList!=null&&talkList.size()!=0){
				if(user.isFirstjoin()){
					out.write("          以下为最近十条历史群聊记录~\n------------------------------------------\n");
					for(String say :talkList){
						out.write(say);
					}
					out.write("------------------------------------------\n          以上为最近十条历史群聊记录~\n------------------------------------------\n");
					out.write("系统：您已进入聊天室.....(请您注意文明用语，谢谢配合)\n------------------------------------------\n");
					user.setFirstjoin(false);
					user.setNumMessage(0);
				}else{
					//存储的消息都打印出来！
					System.out.println(user.getNumMessage()+"条消息");
					out.write(talkList.get(talkList.size()-user.getNumMessage()));
					/*for(int i = user.getNumMessage();i>0;i--){
						out.write(talkList.get(talkList.size()-i));
						//这个是改进之前的 只能接受最新的一条消息
						//out.write(talkList.get(talkList.size()-1));
					}*/
						user.setNumMessage(user.getNumMessage()-1);
						
						
				}
				
			}else{
				out.write("------------------------------------------\n      数据库还没有历史消息记录呢~\n------------------------------------------\n");
				out.write("系统：您已进入聊天室.....(请您注意文明用语，谢谢配合)\n------------------------------------------\n");
				user.setFirstjoin(false);
				user.setNumMessage(0);
				
			}
			
			
			/*if(talk!=null){
				String [] b = talk.split(":");
					//如果是自己说的话，那么久不接受了，本地接受就ok了
				if(b[0].indexOf(user.getUserName())!=-1){
					return;
				}
				
				out.write(talk);
				//this.getServletContext().setAttribute("talk", null);
			}else{
				out.close();
			}*/

			//设置不让接受消息了
			//user.setAccep(false);
			out.close();
			
		}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		}
}
