package com.cn.entity;

import java.io.Serializable;

public class User
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int id;
  private String userName;
  private String userPassword;
  //private boolean accep =true;
  			// 上线告知用户 属性
  private boolean addu = true;
  			// 退出告知属性
  private boolean loninOut =false;
  			//首次加入属性
  private boolean  firstjoin =true;
  private int numMessage = 1;
  int age;
  private int flagnum=0;
  private int flagnum2=1;
  
  public int getFlagnum2() {
	return flagnum2;
}

public void setFlagnum2(int flagnum2) {
	this.flagnum2 = flagnum2;
}

public int getFlagnum() {
	return flagnum;
}

public void setFlagnum(int flagnum) {
	this.flagnum = flagnum;
}

public int getNumMessage() {
	return numMessage;
}

public void setNumMessage(int numMessage) {
	this.numMessage = numMessage;
}

public boolean isFirstjoin() {
	return firstjoin;
}

public void setFirstjoin(boolean firstjoin) {
	this.firstjoin = firstjoin;
}

public boolean isLoninOut() {
	return loninOut;
}

public void setLoninOut(boolean loninOut) {
	this.loninOut = loninOut;
}


  
  public boolean isAddu() {
	return addu;
}

public void setAddu(boolean addu) {
	this.addu = addu;
}

public User(String userName, String userPassword, int age)
  {
    this.userName = userName;
    this.userPassword = userPassword;
    this.age = age;
  }

  public User(int id, String userName, String userPassword, int age)
  {
    this.id = id;
    this.userName = userName;
    this.userPassword = userPassword;
    this.age = age;
  }

 

public int getId()
  {
    return this.id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getUserPassword()
  {
    return this.userPassword;
  }

  public void setUserPassword(String userPassword)
  {
    this.userPassword = userPassword;
  }

  public int getAge()
  {
    return this.age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

@Override
public String toString() {
	return "User [id=" + id + ", userName=" + userName + ", userPassword="
			+ userPassword + ", addu=" + addu
			+ ", loninOut=" + loninOut + ", firstjoin=" + firstjoin
			+ ", numMessage=" + numMessage + ", age=" + age + "]";
}

 
}