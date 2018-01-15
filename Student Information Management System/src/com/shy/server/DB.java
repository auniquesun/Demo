package com.shy.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private final String name="com.mysql.jdbc.Driver";///加载jdbc driver
	private final String url="jdbc:mysql://192.168.112.142/mysql";  //连接到192.168.112.136上的mysql数据库
    private final String db_uname = "root";
//    private final String db_upwd = "";
    private final String db_upwd = "123456";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public DB(){
    	try {
			Class.forName(name);
			try {
				conn = DriverManager.getConnection(url,db_uname,db_upwd);
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("connection error!");
				e.printStackTrace();
			}//创建连接
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("loading driver error!");
			e.printStackTrace();
		}
        
    }
    
    @SuppressWarnings("finally")
	public ResultSet select(String sql,Flag f){
    	try {
    		//这里的flag是一个引用传递，即flag在本函数的改变会反映到调用本函数的方法里flag的值
    		/*in fact, in jdk1.7 
    		 * java.sql.Statement.excute
    		 * Executes the given SQL statement, which "may" return multiple results
    		 */
    		
    		/*经过实验，不管有没有结果
    			stmt.execute(sql)都会返回true
    			所以下面要不要写	f.hasResultSet = stmt.execute(sql);	都可
    		 */
    		

//    		以下注释掉的，测试时用到
//    		f.hasResultSet = stmt.execute(sql);
//    		if(f.hasResultSet == false){
//    			rs = null;
//    			System.out.println("########## no result ##########");
//    		}
//    		else{
    			stmt.execute(sql);
    			rs = stmt.getResultSet();
//    			System.out.println("########## have result ##########");
//        		stmt.close();
//    			conn.close();
//    		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println();
			e.printStackTrace();
		}finally{
			return rs;			
		}
    }
    public boolean modify(String sql){
    	try {
			stmt.executeUpdate(sql);
//			stmt.close();
//			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("update error!");
			e.printStackTrace();
			return false;
		}
    }
    public boolean delete(String sql){
    	try {
			stmt.executeUpdate(sql);
//			stmt.close();
//			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete error!");
			e.printStackTrace();
			return false;
		}
    }
    public boolean insert(String sql){
    	try {
			stmt.executeUpdate(sql);
//			stmt.close();
//			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("insert error!");
			e.printStackTrace();
			return false;
		}
    }
    public void close(){
    	
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }
}
