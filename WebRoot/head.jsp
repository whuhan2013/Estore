<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 
<table width="100%" border="1">
  <tr>
    <td colspan="3" bgcolor="#FFCCFF" height="10"></td>
  </tr>
  <tr>
    <td width="38%"><img src="img/logo1.jpg" width="193" height="122" /></td>
    <td width="50%"><img src="img/logo2.jpg" width="309" height="107" /></td>
    <td width="12%"><p class="abc">
     <c:if test="${sessionScope.user==null }">
   <a href="login.jsp">请登陆</a>
   </c:if>
   <c:if test="${sessionScope.user!=null }">
   [欢迎您，${sessionScope.user.username }]
   </c:if>
    </p>
    <p class="abc"><a href="cart.jsp">[我的购物车]</a><br></p>    </td>
  </tr>
  <tr>
    <td colspan="3" bgcolor="#FFCCFF" height="10px"></td>
  </tr>
  <tr>
    <td colspan="3"><table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="11%" align="center">&nbsp;</td>
        <td width="11%" align="center" class="navi">首页</td>
        <td width="11%" align="center">&nbsp;</td>
        <td width="11%" class="navi" align="center">商品分类</td>
        <td width="11%">&nbsp;</td>
        <td width="11%" align="center" class="navi">活动优惠</td>
        <td width="11%">&nbsp;</td>
        <td width="11%" align="center" class="navi">关于我们</td>
        <td width="11%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>