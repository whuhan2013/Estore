<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Estore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body topmargin="0" background="images/bg.jpg" >
    <table width="80%" border="1" align="center">
  <tr>
    <td height="96" colspan="2" align="center" valign="middle">
    <!-- 引入head.jsp -->
    <jsp:include flush="true" page="head.jsp"></jsp:include>
    </td>
  </tr>
  <tr>
    <td width="21%" height="317" align="center" valign="top">
     <!-- 引入left.jsp -->
    <jsp:include flush="true" page="left.jsp"></jsp:include>
    </td>
    <td width="73%" align="center" valign="top">
    <!-- 引入right.jsp -->
    <jsp:include flush="true" page="right.jsp"></jsp:include>
    </td>
  </tr>
  <tr>
    <td height="62" colspan="2" align="center" valign="middle">
    <!-- 引入tail.jsp -->
    <jsp:include page="tail.jsp" flush="true"></jsp:include>
    </td>
  </tr>
</table>
  </body>
</html>
