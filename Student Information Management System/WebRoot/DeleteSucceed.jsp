<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DeleteSucceed.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
	function select_info(){
  		window.location.href="Select.jsp";
  	}
  	function back_homepage(){
  		window.location.href="LoginSucceed.jsp";
  	}
  	
	</script>

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
      background:url(img/whale.jpg);
      background-size:100% 100%;
    }

    .button {
    width:120px;
    height:27px;
    background-color: #4CAF50; /* Green */
      border: none;
      color: white;
      padding: 16px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 12px;
      /*margin: 4px 2px;*/
      -webkit-transition-duration: 0.4s; /* Safari */
      transition-duration: 0.4s;
      cursor: pointer;
    }
    
    .buttonGreen {
      margin-top:1%;
      margin-left:15%;
        background-color: green; 
        color: white; 
        /*border: 2px solid #4CAF50;*/
    }
    
    .buttonGreen:hover {
        background-color: white;
        color: green;
    }
  
    /*.buttonBlue {
    margin-left:15%;
        background-color: white; 
        color: black; 
        border: 2px solid #008CBA;
    }
  
    .buttonBlue:hover {
        background-color: #008CBA;
        color: white;
    }*/
  </style>
  </head>
  
  <body class="body">
    <!-- This is my JSP page. <br> -->

    <div align="center">Delete Succeed!	</div>

                  <form action="servlet/SelectRespond"  method="post"> 
    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
      <tr>
        <td align="left" valign="top">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
          <tr>
           <td width="90%" align="left" valign="middle">
               <span>&nbsp;Admin :</span>
                
               <input type="text" name="id" class="text-word">
               <input type="submit" value="SELECT" class="text-but">

               <!-- <button onclick="back_homepage()" class="button buttonGreen">back</button> -->

             </td>
            
          </tr>
        </table>
        </td>
      </tr>

    </table>
             </form>

    <!-- <button onclick="select_info()" class="button buttonGreen">select</button> -->
  </body>
</html>
