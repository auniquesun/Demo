package com.shy.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteRespond extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteRespond() {
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
		
		String s = request.getParameter("id");
		
		String regex=".*[a-zA-Z]+.*";  
		Matcher m = Pattern.compile(regex).matcher(s);
        if(m.matches() == true){
        	System.out.println("##### letter cannot be included in ID #####");
        	response.sendRedirect("/Student_Information_Management_System/DeleteFail.jsp");
        }else{
        	int id = Integer.parseInt(s);
    		
    		DB db = new DB();
    		
//    		先要看有没有这个人
    		Flag f = new Flag();
    		ResultSet rs = db.select("select * from student where id ='"+id+"';", f);
    		
    		int count = 0;
    		try {
    			while (rs.next()) {
    				
    				//把 数据库中id转换成整数,	attention: rs.getString("id") 中的 id 要加引号
    			    if(id == Integer.parseInt(rs.getString("id"))){
    			    	count++;
    			    	break;
    			    }
    			    
    			}
    			
//    			如果没有这个人			
    			if(count == 0)
    				response.sendRedirect("/Student_Information_Management_System/DeleteFail.jsp");
    			else{
//    				如果有个人，删除之
    				Boolean flag = db.delete("delete from student where id ='"+id+"';");
    				db.close();
    				if(flag == true)
    					response.sendRedirect("/Student_Information_Management_System/DeleteSucceed.jsp");
    				else
    					response.sendRedirect("/Student_Information_Management_System/DeleteFail.jsp");
    			}
    			
    		} catch (NumberFormatException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        

//		response.sendRedirect("DeleteSucceed.jsp");
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
