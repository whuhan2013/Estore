<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'prodInfo.jsp' starting page</title>
    
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
   <font color="red"><h1>${prod.name }</h1><hr></font>
  	<table width="100%" >
  		<tr>
  			<td><img src="ImgServlet?imgurl=${prod.imgurl }"/></td>
  			<td>
  				商品名称:${prod.name }<br>
  				商品种类:${prod.category }<br>
  				商品库存:${prod.pnum }<br>
  				商品价格:${prod.price }<br>
  				商品描述:${prod.description }<br>
  				<a href="AddCartServlet?id=${prod.id }"><img src="img/buy.bmp"/></a><br>
  			</td>
  		</tr>
  	</table>
  	</center>
  </body>
</html>
