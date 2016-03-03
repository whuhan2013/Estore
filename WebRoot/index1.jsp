<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index1.jsp' starting page</title>
    
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
   <h1>Estore商城</h1><hr>
   <c:if test="${sessionScope.user==null }">
   欢迎光临，游客:
   <a href="register.jsp">注册</a>
   <a href="login.jsp">登陆</a>
   </c:if>
   <c:if test="${sessionScope.user!=null }">
   欢迎回来，${sessionScope.user.username }<br>
   <a href="addProd.jsp">添加商品</a><br>
   <a href="ProdListServlet">商品列表</a><br>
  	<a href="cart.jsp">查看购物车</a><br>
  	<a href="OrderListServlet">订单查询</a><br>
  	<a href="SaleListServlet">销售榜单下载</a><br>
  	<a href="${pageContext.request.contextPath }/PageServlet?thispage=1">分页查询客户</a>
   <a href="LogoutServlet">注销</a>
   </c:if>
   </center>
  </body>
</html>
