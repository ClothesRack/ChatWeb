package com.cn.dao;


import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cn.entity.User;
import com.cn.util.DbHelper;




public class UserDAO
{
	//插入数据
  public void registerUser(User user)
  {
    String sql = "insert into t_user values(0,?,?,?)";
    try
    {
      DbHelper.executeUpdate(sql, new Object[] { user.getUserName(), user.getUserPassword(), Integer.valueOf(user.getAge()) });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public User loginUser(String userName, String userPassword)
  {
    String sql = "select * from t_user where userName=? and userPassword=?";
    try
    {
      ResultSet rs = DbHelper.executeQuery(sql, new Object[] { userName, userPassword });
      User user = null;
      //挨个从服务器遍历查找当前用户是否存在
      while (rs.next()) {
        user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
      }
      rs.close();
      return user;
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }

  public List<User> list()
  {
    String sql = "select *from t_user";
    List<User> list = new ArrayList<User>();
    try {
      ResultSet rs = DbHelper.executeQuery(sql, null);
      while (rs.next()) {
        list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }

  public void deleteUserById(Integer id) {
    int flag = 0;
    String sql = "delete from t_user where id=?";
    try
    {
      flag = DbHelper.executeUpdate(sql, new Object[] { id });
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (flag == 1) {
      SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
      String dateTime = dateFm.format(new Date());
      System.out.println("id：" + id + ":" + "被删除了...." + dateTime);
    } else {
      System.out.println("数据库并没有该信息");
    }
  }
  public boolean update(String userName,Integer age, String userPassword){
	 
	  int flag = 0;
	  String sql = "update  t_user set age="+age+",userPassword='"+userPassword+"' where userName=?";
	   try
	    {
	      flag = DbHelper.executeUpdate(sql, new Object[] {userName});
	    } catch (Exception e) {
	      e.printStackTrace();
	      //return false;
	    }
	   if (flag == 1) {
		   return true;
	   }
	return false;
  }
}