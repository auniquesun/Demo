<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Insert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<link href="css.css" type="text/css" rel="stylesheet" />
	<link href="main.css" type="text/css" rel="stylesheet" />
	<link rel="shortcut icon" href="images/main/favicon.ico" />
	
	<style type="text/css">
		@import url(https://fonts.googleapis.com/css?family=Exo:100,200,400);
	  	@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);
	
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
	  		background:url(img/ocean-bg.jpg);
	  		background-size:100% 100%;
	  	}
	  	.login{
			position: absolute;
			top: calc(20%);
			left: calc(35%);
			height: 150px;
			width: 350px;
			padding: 10px;
			z-index: 2;
		}
	  	.login input[type=text]{
			width: 250px;
			height: 30px;
			background: transparent;
			border: 1px solid rgba(255,255,255,0.6);
			border-radius: 2px;
			color: #fff;
			font-family: 'Exo', sans-serif;
			font-size: 16px;
			font-weight: 400;
			padding: 4px;
		}

		.button {
	  	width:260px;
	  	height:40px;
		background-color: #4CAF50; /* Green */
	    border: none;
	    color: white;
	    padding: 16px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 12px;
	    margin: 4px 2px;
	    -webkit-transition-duration: 0.4s; /* Safari */
	    transition-duration: 0.4s;
	    cursor: pointer;
		}
		.buttonGreen {
			margin-top:26%;
			margin-left:40%;
		    background-color: #4CAF50; 
		    color: black; 
		    /*border: 2px solid white;*/
		}
		
		.buttonGreen:hover {
		    background-color: white;
		    color: #4CAF50;
		}
	</style>
  </head>
  
  <body class="body">
  
  <table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
	  <tr>
	    <td width="99%" align="left" valign="top">Your Location : INSERT&nbsp;RECORD</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
	  		<tr>
	   		 <td width="90%" align="left" valign="middle">
		         <span>&nbsp;</span>
	         </td>
	  		  
	  		</tr>
		  </table>
	    </td>
	  </tr>

	</table>

  	<form action="servlet/InsertRespond"  method="post">
	    <div class="login">
	    ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id">	<br>
	    Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name">	<br>
	    Gender &nbsp;&nbsp;&nbsp;<input type="text" name="gender">	<br>
	    Birthday &nbsp;<input type="text" name="birthday">	<br>
	    School&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="school">	<br>
	    Major&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="major">	<br>
	    <br>
	    </div>
	    <input type="submit" value="INSERT" class="button buttonGreen">
    </form>
  </body>
</html>
