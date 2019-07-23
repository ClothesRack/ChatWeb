package com.cn.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHelper
{
  private static final String dirverclass = "com.mysql.jdbc.Driver";
  private static final String jdbcUrl = "jdbc:mysql://localhost:3306/javadom?user=root&password=8023&useUnicode=true&characterEncoding=UTF8";
  private static Connection conn = null;
  private static PreparedStatement pst = null;

  public static Connection getConnection() throws Exception{
    Class.forName("com.mysql.jdbc.Driver");

    return DriverManager.getConnection("jdbc:mysql://localhost:3306/javadom?user=root&password=8023&useUnicode=true&characterEncoding=UTF8");
  }
  public static ResultSet executeQuery(String sql, Object[] args) throws Exception {
    if (conn == null) {
      conn = getConnection();
    }
    pst = conn.prepareStatement(sql);
    setValues(pst, args);
    return pst.executeQuery();
  }

  public static int executeUpdate(String sql, Object[] args)throws Exception{
	  
    if (conn == null) {
    	conn = getConnection();
    }
    pst = conn.prepareStatement(sql);
    setValues(pst, args);
    return pst.executeUpdate();
  }
  private static void setValues(PreparedStatement pst, Object[] args) throws SQLException {
    if ((args != null) && (args.length > 0))
      for (int i = 0; i < args.length; i++)
        pst.setObject(i + 1, args[i]);
  }
}