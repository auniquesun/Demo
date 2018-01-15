<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'LoginSucceed.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
  	function delete_info(){
  		window.location.href="Delete.jsp";
  	}
  	function select_info(){
  		window.location.href="Select.jsp";
  	}
  	function modify_info(){
  		window.location.href="Modify.jsp";
  	}
  	function insert_info(){
  		window.location.href="Insert.jsp";
  	}
  </script>
  
  <style>
	  	/* Google Webfonts */
	@import url(https://fonts.googleapis.com/css?family=Oswald);
	
	/* Basic Styles */	
	/* a */
	h2:hover {
	  color: #ADD8E6;
	  text-shadow: 
	    /* Color 1 */
	    1px 1px #61b4de,
	    /* Color 2 */
	    2px 2px #91c467,
	    /* Color 3 */
	    3px 3px #f3a14b,
	    /* Color 4 */
	    4px 4px #e84c50,
	    /* Color 5 */
	    5px 5px #4e5965;
	}
	h2 {
	  text-align: center;
	  color: #BBFFFF;
	  font-family: Oswald;
	  font-size: 4em;
	  transition: 0.5s all ease;
	  /*text-transform: uppercase;*/
	  text-decoration: none;
	  text-shadow: 
	    /* Color 1 */
	    1px 1px #61b4de, 2px 2px #61b4de, 3px 3px #61b4de, 4px 4px #61b4de, 5px 5px #61b4de,
	    /* Color 2 */
	    6px 6px #91c467, 7px 7px #91c467, 8px 8px #91c467, 9px 9px #91c467, 10px 10px #91c467,
	    /* Color 3 */
	    11px 11px #f3a14b, 12px 12px #f3a14b, 13px 13px #f3a14b, 14px 14px #f3a14b, 15px 15px #f3a14b,
	    /* Color 4 */
	    16px 16px #e84c50, 17px 17px #e84c50, 18px 18px #e84c50, 19px 19px #e84c50, 20px 20px #e84c50,
	    /* Color 5 */
	    21px 21px #4e5965, 22px 22px #4e5965, 23px 23px #4e5965, 24px 24px #4e5965, 25px 25px #4e5965;
	}
  	.body{
  		background:url(img/ocean-bg.jpg);
  		background-size:100% 100%;
  	}
  	
  	.button {
  	width:200px;
  	height:50px;
	background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
	}
	
	.buttonGreen {
		margin-top:1%;
		margin-left:15%;
	    background-color: white; 
	    color: gray; 
	    border: 2px solid #4CAF50;
	}
	
	.buttonGreen:hover {
	    background-color: #4CAF50;
	    color: white;
	}
	
		.buttonBlue {
		margin-left:15%;
		    background-color: white; 
		    color: black; 
		    border: 2px solid #008CBA;
		}
	
		.buttonBlue:hover {
		    background-color: #008CBA;
		    color: white;
		}
	  
	  
	.buttonRed {
	margin-left:15%;
	    background-color: white; 
	    color: black; 
	    border: 2px solid #f44336;
	}
	
	.buttonRed:hover {
	    background-color: #f44336;
	    color: white;
	}
	.buttonBlack {
	margin-left:15%;
	    background-color: white;
	    color: black;
	    border: 2px solid #555555;
	}
	
	.buttonBlack:hover {
	    background-color: #555555;
	    color: white;
	}

/*	.textarea{
		margin-left: 50%;
		margin-top: 40%;
		width: 20%;
		height: 15%;
	}*/
  </style>
  
  </head>
  
  <body  class="body">  <!-- align="center" -->
    <!-- This is my JSP page. <br> -->
    <h2>Welcome to Student IMS</h2>
    <button onclick="select_info()" class="button buttonGreen">Select Record</button>
    <br>
    <button onclick="modify_info()" class="button buttonBlue">Modify Record</button>
    <br>
    <button onclick="delete_info()" class="button buttonRed">Delete Record</button>
    <br>
    <button onclick="insert_info()" class="button buttonBlack" style="width: 199px; ">Insert Record</button>
    
  </body>
    <!-- <textarea class="textarea">
    	hello
    </textarea> -->
  
</html>
