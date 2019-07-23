package com.cn.filter;


import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter
  implements Filter
{
  private String defaultEncoding = "UTF-8";

  public void destroy()
  {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    HttpServletResponse httpServletResponse = (HttpServletResponse)response;

    httpServletRequest.setCharacterEncoding(this.defaultEncoding);
    httpServletResponse.setCharacterEncoding(this.defaultEncoding);

    chain.doFilter(httpServletRequest, httpServletResponse);
  }

  public void init(FilterConfig config)
    throws ServletException
  {
    String encoding = config.getInitParameter("encoding");
    if ((encoding != null) && (!"".equals(encoding))) {
      this.defaultEncoding = encoding;
    }
    System.out.println("CharacterEncodingFilterinit方法执行了....");
  }
}