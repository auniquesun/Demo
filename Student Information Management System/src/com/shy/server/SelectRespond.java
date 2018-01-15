package com.shy.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.Integer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectRespond extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectRespond() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("name", "sims");
		session.setMaxInactiveInterval(30*60);
		
		response.setContentType("text/html");
		
		//����ѧ����Ϣ
		ArrayList<Student> list = new ArrayList<Student>();
		
		String s = request.getParameter("id");
		
		//��������ʽ�ж������ѧ���Ƿ������ĸ
		String regex=".*[a-zA-Z]+.*";  
		Matcher m = Pattern.compile(regex).matcher(s);
        if(m.matches() == true){
        	System.out.println("##### letter cannot be included in ID #####");
        	response.sendRedirect("/Student_Information_Management_System/SelectFail.jsp");
        }
        else{
        	int id = Integer.parseInt(s);

    		DB db = new DB();
    		String sql = "select * from student where id ='"+id+"'";
    		Flag f = new Flag();//���캯������hasResultSet��Ϊfalse
//    		System.out.println("########## ��ʼ�� " + f.hasResultSet + " ##########");
    		
    		/*
    		 * ���� rs = db.select(sql,f); ÿ�η��صĽ������Ϊ��
    		 * �����ݿ�����Ҫ��ļ�¼ʱ����һ���ж�while������rs.next()��Ϊ��
    		 * ���ǵ����ݿ���û��������¼ʱ�� ��һ���ж�while������rs.next()Ϊ��
    		 */
    		ResultSet rs = db.select(sql,f);
//    		System.out.println("########## ִ��select�� " + f.hasResultSet + " ##########");
//    		if(f.hasResultSet == true){
    			
    			try {
    				int count = 0;
					while (rs.next()) {  
					    //������һ��ʵ����,������Ŵ����ݿ����õ�������  
					    Student student = new Student();  
					    student.setID(rs.getString("id"));
					    student.setName(rs.getString("name"));  
					    student.setGender(rs.getString("gender"));  
					    student.setBirthday(rs.getString("birthday"));  
					    student.setSchool(rs.getString("school"));
					    student.setMajor(rs.getString("major"));
					    list.add(student);
					    count++;
					}
					db.close();
					if(count == 0)
						request.getRequestDispatcher("/SelectFail.jsp").forward(request, response);
					else{
						request.setAttribute("Students", list);
						request.getRequestDispatcher("/SelectSucceed.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//    		}
    			
        }
			
		
		
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
