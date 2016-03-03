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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <script>
 
window.onload= function linkPage()
{
	
 window.location.href="PageServlet?thispage=1"; 	
		
}

</script>
  
  <!-- <body onLoad="linkPage()">
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
  </body> -->
  
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
