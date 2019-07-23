package com.cn.entity;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.cn.servlet.LoginOut;
/**
 * 
 * @author Raven
 * @date ����5:44:56
 * @version
 * 		
 */
public class ThreadFlag  implements Runnable {
	private ServletContext servletContext ;
	public ThreadFlag(ServletContext servletContext){
		this.servletContext = servletContext;
	}

	/**
	 * 
	 * �ж��û�δ�˳�ҳ�� ֱ�����߼���ɾ����
	 */
	public void run() {
		while(true){
			
			Map<User, Boolean> map = (Map<User, Boolean>) servletContext.getAttribute("map");
			try {
				if(map!=null){
					if(map.size()!=0)
					synchronized (map) {
						for(Map.Entry<User, Boolean> entry: map.entrySet()){
								//�������������ͬ ��ô ���Ѿ�������   ��Ϊ���Ѿ�10��û������saychat��������
							if(entry.getKey().getFlagnum()==entry.getKey().getFlagnum2()){
								System.out.println(entry.getKey().getUserName()+"û���˳�ֱ�ӹر�ҳ�� ,���Ѿ���������");
								map.remove(entry.getKey());
								servletContext.setAttribute("logoutUserName", entry.getKey().getUserName());
								for(Map.Entry<User, Boolean> entry2: map.entrySet()){
									//�������û������Զ�Ҫ֪�������������
									entry2.getKey().setLoninOut(true);
								}
								continue;
							}
							//����flagnum2=flagnum
							entry.getKey().setFlagnum2(entry.getKey().getFlagnum()); 
						}
					}
					
			
					
				} 
				
				
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
