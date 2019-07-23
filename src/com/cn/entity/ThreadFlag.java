package com.cn.entity;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.cn.servlet.LoginOut;
/**
 * 
 * @author Raven
 * @date 下午5:44:56
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
	 * 判断用户未退出页面 直接在线集合删掉他
	 */
	public void run() {
		while(true){
			
			Map<User, Boolean> map = (Map<User, Boolean>) servletContext.getAttribute("map");
			try {
				if(map!=null){
					if(map.size()!=0)
					synchronized (map) {
						for(Map.Entry<User, Boolean> entry: map.entrySet()){
								//如果两个数字相同 那么 他已经下线了   因为他已经10秒没有再向saychat发请求了
							if(entry.getKey().getFlagnum()==entry.getKey().getFlagnum2()){
								System.out.println(entry.getKey().getUserName()+"没有退出直接关闭页面 ,现已经将他清理！");
								map.remove(entry.getKey());
								servletContext.setAttribute("logoutUserName", entry.getKey().getUserName());
								for(Map.Entry<User, Boolean> entry2: map.entrySet()){
									//让所有用户的属性都要知道这个人下线了
									entry2.getKey().setLoninOut(true);
								}
								continue;
							}
							//设置flagnum2=flagnum
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
