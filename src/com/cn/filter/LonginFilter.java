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
      System.out.println("���˻�û��½��ͼ���ʵ�¼ҳ��....");
    }
    else {
      chain.doFilter(httpServletRequest, httpServletResponse);
      System.out.println("�Ѿ���¼���Է���.....");
    }
  }

  public void init(FilterConfig filterConfig)
    throws ServletException
  {
  }
}