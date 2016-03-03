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
    
    <title>My JSP 'orderList.jsp' starting page</title>
    
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
    <h1>订单列表</h1><hr>
  	<c:forEach items="${requestScope.list}" var="olf">
  		<h3>
  		订单号:${olf.id }<br>
  		</h3>
		用户名称 :${olf.username }<br>
		订单金额 :${olf.money }<br>
		支付状态 :
			<c:if test="${olf.paystate == 0}">
				<font color="red">未支付</font>
				<a href="DelOrderServlet?id=${olf.id }">订单删除</a>
				<a href="pay.jsp?id=${olf.id }&money=${olf.money }">在线支付</a>
			</c:if>
			<c:if test="${olf.paystate != 0}">
				<font color="blue">已支付</font>
			</c:if>
		<br>
		收货地址 :${olf.receiverinfo }<br>
		下单时间 :${olf.ordertime }<br>
		<table width="100%" border="1">
			<tr>
				<th>商品名称</th>
				<th>商品种类</th>
				<th>购买数量</th>
				<th>商品单价</th>
				<th>总金额</th>
			</tr>	
			<c:forEach items="${olf.prodMap}" var="entry">
				<tr>
					<td>${entry.key.name }</td>
					<td>${entry.key.category }</td>
					<td>${entry.value }</td>
					<td>${entry.key.price }</td>
					<td>${entry.key.price * entry.value }</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
  	</c:forEach>
  </body>
</html>
