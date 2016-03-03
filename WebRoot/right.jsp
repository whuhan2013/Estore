<%@ page language="java" import="java.util.*,com.zj.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//使用GoodsBeanBO完成分页任务

Page mypage=new Page();
mypage=(Page)request.getAttribute("page");
//接收pageNow
String  s_pageNow=mypage.getThispage()+"";
int pageNow=1;
if(s_pageNow!=null)
{
	pageNow=Integer.parseInt(s_pageNow);
	
}
System.out.println(s_pageNow);
	
	

//默认显示第一页
List al=(ArrayList)mypage.getList();
System.out.println("大小为"+al.get(1));
//得到共有多少页
int pageCount=mypage.getCountpage();
%>



<table width="100%" border="1" class="abc">
  <tr>
    <td height="53" colspan="3" align="center" valign="middle"><img src="images/shang.jpg" width="255" height="50" /></td>
  </tr>
  
  <%
      int time=0;
       for (int i=0;i<2;i++)
       {
    	   //打印两行，每行有3个货物，也有可能货物不足
    	   %>
    	   <tr>
    	   <% 
    	   //取出3个
    	   for(int j=0;j<3;j++)
    	   {
    		   Product prod=new Product();
    		   if(time>=al.size())
    		   {
    				prod.setId(0+"");
    				prod.setDescription("没有书了");
    				prod.setImgurl("meiyou.gif");
    				prod.setPrice(0.0f);
    				prod.setName("暂时无货");
     				
     				//System.out.println("ABC"+al.size());
     			}else{
     			
     			 prod=(Product)al.get(time);//??
     					 System.out.println("名字为"+prod.getName());
     				time++;
     				}
    		   %>
    		   <td width="33%" align="center" valign="middle"><table width="100%" height="170" border="1" class="abc">
      <tr>
        <td width="33%" rowspan="3" align="left" valign="top"><img src="ImgServlet?imgurl=<%=prod.getImgurl() %>" width="110" height="122" /></td>
        <td width="67%" height="42"><p>&nbsp;</p></td>
      </tr>
      <tr>
        <td height="36" align="left" valign="top"><a href="ProdInfoServlet?id=<%=prod.getId() %>"> <%=prod.getName() %></a></td>
      </tr>
      <tr>
        <td height="35" align="left" valign="top">价格：<%=prod.getPrice() %> </td>
      </tr>
      <tr>
        <td height="43" colspan="2" align="left" valign="top">简单介绍:<%=prod.getCategory() %> </td>
      </tr>
    </table></td>
    		   <% 
    	   }
    	   %>
    	   </tr>
    	   <%

    	   if(i==0)
    	   {
    		   %>
    		   <tr>
           <td colspan="3" align="center" valign="middle" bgcolor="#FFCCFF" height="10"></td>
            </tr>
    		   <%
    	   }
       }
  
  %>
 
  <tr>
    <td colspan="3" align="center" valign="middle">
    共${page.countrow }条记录
  	共${page.countpage }页
  	<a href="${pageContext.request.contextPath }/PageServlet?thispage=${page.firstpage }">首页</a>
  	<a href="${pageContext.request.contextPath }/PageServlet?thispage=${page.prepage }">上一页</a>
  	
  	<!-- 分页逻辑开始 -->
  	<c:if test="${page.countpage<=5}">
  		<c:set var="begin" value="1" scope="page"></c:set>
  		<c:set var="end" value="${page.countpage}" scope="page"></c:set>
  	</c:if>
  	<c:if test="${page.countpage>5}">
		<c:choose>
			<c:when test="${page.thispage<=3}">
				<c:set var="begin" value="1" scope="page"></c:set>
  				<c:set var="end" value="5" scope="page"></c:set>
			</c:when>
			<c:when test="${page.thispage>=page.countpage-2}">
				<c:set var="begin" value="${page.countpage-4}" scope="page"></c:set>
  				<c:set var="end" value="${page.countpage}" scope="page"></c:set>
  			</c:when>
  			<c:otherwise>
  				<c:set var="begin" value="${page.thispage-2}" scope="page"></c:set>
  				<c:set var="end" value="${page.thispage+2}" scope="page"></c:set>
  			</c:otherwise>
		</c:choose>
  	</c:if>
  	
  	<c:forEach begin="${begin}" end="${end}" step="1" var="i">
  		<c:if test="${i == page.thispage}">
  			${i }
  		</c:if>
  		<c:if test="${i != page.thispage}">
  			<a href="${pageContext.request.contextPath }/PageServlet?thispage=${i}">${i }</a>
  		</c:if>
  	</c:forEach>
  	
  	<!-- 分页逻辑结束 -->
  	
  	<a href="${pageContext.request.contextPath }/PageServlet?thispage=${page.nextpage }">下一页</a>
  	<a href="${pageContext.request.contextPath }/PageServlet?thispage=${page.lastpage }">尾页</a>
  	
     </td>
  </tr>
</table>


