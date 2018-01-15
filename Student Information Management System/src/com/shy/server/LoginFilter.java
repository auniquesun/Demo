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
		
		// ����û������URI
		String path = servletRequest.getRequestURI();
		String name = (String)session.getAttribute("name");
		
		// ��½ҳ���������
         if(path.indexOf("/index.jsp") > -1) {
             chain.doFilter(servletRequest, servletResponse);
             return;
         }
 
         // �ж����û��ȡ��Session��name����,����,˵���û�û�е�¼,����ת����¼ҳ��
         if (name == null || "".equals(name)) {
             // ��ת����½ҳ��
             servletResponse.sendRedirect("/Student_Information_Management_System/index.jsp");
         } else {
             // �Ѿ���½,�����˴�����
             chain.doFilter(request, response);
         }
	}

}
