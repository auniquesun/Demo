<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SelectSucceed.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <style type="text/css">
  body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
    #searchmain{ font-size:12px;}
    #search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF; float:left}
    #search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
    #search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
    #search form input.text-but{height:24px; line-height:24px; width:55px; background:url(images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
    #search a.add{ background:url(images/main/add.jpg) no-repeat -3px 7px #548fc9; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF; float:right}
    #search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
    #main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
    #main-tab th{ font-size:12px; background:url(images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;}
    #main-tab td{ font-size:12px; line-height:40px;}
    #main-tab td a{ font-size:12px; color:#548fc9;}
    #main-tab td a:hover{color:#565656; text-decoration:underline;}
    .bordertop{ border-top:1px solid #ebebeb}
    .borderright{ border-right:1px solid #ebebeb}
    .borderbottom{ border-bottom:1px solid #ebebeb}
    .borderleft{ border-left:1px solid #ebebeb}
    .gray{ color:#dbdbdb;}
    td.fenye{ padding:10px 0 0 0; text-align:right;}
    .bggray{ background:#f9f9f9}
    .body{
      background:url(img/dolphon.jpg);
      background-size:100% 100%;
    }
  </style>
	<script>
  	function back_homepage(){
  		window.location.href="LoginSucceed.jsp";
  	}
  	
	</script>
  </head>
  
  <body class="body">
    <!--This is my JSP page. <br>-->
    <div align = "center">Result Returned</div>
    
    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
      <tr>
        <td align="left" valign="top">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
          <tr>
           <td width="90%" align="left" valign="middle">
               <span>&nbsp;Admin :</span>
               
               <!-- <input type="text" name="id" class="text-word">
               <input type="submit" value="SELECT" class="text-but"> -->
                   <button onclick="back_homepage()">BACK</button>

             </td>
            
          </tr>
        </table>
        </td>
      </tr>

    </table>
    
    <%@ page import="com.shy.server.Student"%>
     <!-- 用jsp语句,将servlet传过来的list数据拿到,并放到一个list中 -->  
    <%  
        ArrayList<Student> list = (ArrayList<Student>) request.getAttribute("Students");  
    %>   
    
    <!-- 声明一个表格,这是表头 -->  
    <h2 align="center">STUDENT INFORMATION</h2>  
    <table border = 1px align = "center">  
        <tr>  
            <th>ID</th>  
            <th>Name</th>  
            <th>Gender</th>  
            <th>Birthday</th>
            <th>School</th>  
            <th>Major</th>  
        </tr> 
        <!-- 继续使用jsp语句 循环放入存放于list中的student实体类中的数据 -->  
        <%  
            for(int i = 0;i<list.size();i++){  
                Student student = (Student)list.get(i);
        %>  
                <tr>  
                	<th><%=student.getID()%></th>  
	                <th><%=student.getName()%></th>  
	                <th><%=student.getGender()%></th>  
	                <th><%=student.getBirthday()%></th>  
	                <th><%=student.getSchool()%></th>
	                <th><%=student.getMajor()%></th>
                </tr>  
        <% 
        	}  
        %>
    </table>
    <br>
    <!-- <button onclick="back_homepage()">back</button> -->
  </body>
</html>
