package com.cn.filter;



import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LonginFilter
  implements Filter
{
  public void destroy()
  {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    HttpServletResponse httpServletResponse = (HttpServletResponse)response;
    if (httpServletRequest.getSession().getAttribute("currentUser") == null)
    {
      httpServletResponse.sendRedirect("/chat/login.jsp");
      System.out.println("有人还没登陆企图访问登录页面....");
    }
    else {
      chain.doFilter(httpServletRequest, httpServletResponse);
      System.out.println("已经登录可以访问.....");
    }
  }

  public void init(FilterConfig filterConfig)
    throws ServletException
  {
  }
}