package com.shy.server;

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

public class LoginFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		HttpSession session = servletRequest.getSession();
		
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		String name = (String)session.getAttribute("name");
		
		// 登陆页面无需过滤
         if(path.indexOf("/index.jsp") > -1) {
             chain.doFilter(servletRequest, servletResponse);
             return;
         }
 
         // 判断如果没有取到Session的name属性,若无,说明用户没有登录,就跳转到登录页面
         if (name == null || "".equals(name)) {
             // 跳转到登陆页面
             servletResponse.sendRedirect("/Student_Information_Management_System/index.jsp");
         } else {
             // 已经登陆,继续此次请求
             chain.doFilter(request, response);
         }
	}

}
