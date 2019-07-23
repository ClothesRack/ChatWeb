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
			//����������һ�� ѭ��������ֱ����һ������
			while(user.getFlagnum()==flagnum){	
				flagnum = random.nextInt(1000);
				System.out.println("�ظ����ã�"+user.getFlagnum());
			}
			//System.out.println("���ã�"+user.getFlagnum());
			if(user.getFlagnum()==user.getFlagnum2())
					//�������������ͬ����ô���¸�flag��һ��ֵ������������������ͬ
			user.setFlagnum(random.nextInt(1000));
			Map<User, Boolean> map = (Map<User, Boolean>) this.getServletContext().getAttribute("map");

			String talk = (String) this.getServletContext().getAttribute("talk");
			
			if(user.isAddu()){
				String adduname = (String) this.getServletContext().getAttribute("loginuserName");
				out.write("------------------------------------------\nϵͳ��"+adduname+",�ѽ���Ⱥ��"+"  "+dateTime+"\n"+"------------------------------------------\n");
				user.setAddu(false);
			}
			if(user.isLoninOut()){
				String adduname = (String) this.getServletContext().getAttribute("logoutUserName");
				out.write("------------------------------------------\nϵͳ��"+adduname+",�˳���Ⱥ��"+"  "+dateTime+"\n"+"------------------------------------------\n");
				user.setLoninOut(false);
			}
			if(/*!user.getAccep()*/user.getNumMessage()==0){
				if(!map.containsKey(user)){
					out.write("�������ո����������ˣ��������µ�¼лл");
					user = null;
					req.getSession().setAttribute("currentUser", null);
				}
				return;
			}
			//req.getSession().setAttribute("currentUser", user);
			List<String> talkList = (List<String>) getServletContext().getAttribute("talksList");
			if(talkList!=null&&talkList.size()!=0){
				if(user.isFirstjoin()){
					out.write("          ����Ϊ���ʮ����ʷȺ�ļ�¼~\n------------------------------------------\n");
					for(String say :talkList){
						out.write(say);
					}
					out.write("------------------------------------------\n          ����Ϊ���ʮ����ʷȺ�ļ�¼~\n------------------------------------------\n");
					out.write("ϵͳ�����ѽ���������.....(����ע���������лл���)\n------------------------------------------\n");
					user.setFirstjoin(false);
					user.setNumMessage(0);
				}else{
					//�洢����Ϣ����ӡ������
					System.out.println(user.getNumMessage()+"����Ϣ");
					out.write(talkList.get(talkList.size()-user.getNumMessage()));
					/*for(int i = user.getNumMessage();i>0;i--){
						out.write(talkList.get(talkList.size()-i));
						//����ǸĽ�֮ǰ�� ֻ�ܽ������µ�һ����Ϣ
						//out.write(talkList.get(talkList.size()-1));
					}*/
						user.setNumMessage(user.getNumMessage()-1);
						
						
				}
				
			}else{
				out.write("------------------------------------------\n      ���ݿ⻹û����ʷ��Ϣ��¼��~\n------------------------------------------\n");
				out.write("ϵͳ�����ѽ���������.....(����ע���������лл���)\n------------------------------------------\n");
				user.setFirstjoin(false);
				user.setNumMessage(0);
				
			}
			
			
			/*if(talk!=null){
				String [] b = talk.split(":");
					//������Լ�˵�Ļ�����ô�ò������ˣ����ؽ��ܾ�ok��
				if(b[0].indexOf(user.getUserName())!=-1){
					return;
				}
				
				out.write(talk);
				//this.getServletContext().setAttribute("talk", null);
			}else{
				out.close();
			}*/

			//���ò��ý�����Ϣ��
			//user.setAccep(false);
			out.close();
			
		}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		}
}
