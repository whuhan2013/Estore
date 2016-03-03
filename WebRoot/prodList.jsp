<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'prodList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
   <h1>Estore商品列表</h1>
   <table width="100%" style="text-align: center;">
   <c:forEach items="${requestScope.list}" var="prod">
   <tr>
   <td width="20%"><a href="ProdInfoServlet?id=${prod.id }"><img src="ImgServlet?imgurl=${prod.imgurls }" style="cursor: pointer;"/></a></td>
   <td width="40%">
   ${prod.name }<br>
   ${prod.price }<br>
   ${prod.category }<br>
   ${prod.pnum }<br>
   
   </td>
   <td width="40%">
   <c:if test="${prod.pnum>0 }">
   <font color="blue">有货</font>
   </c:if>
   <c:if test="${prod.pnum<=0 }">
   <font color="red">缺货</font>
   </c:if>
   </td>
   </tr>
   <tr>
   <td colspan="3"><hr></td>
   </tr>
   </c:forEach>
   </table>
   </center>
  </body>
</html>
