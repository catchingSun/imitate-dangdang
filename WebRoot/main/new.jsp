<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>最新上架图书
</h2>
<div class="book_c_04">

	<!--最新上架开始-->
	<c:forEach var="c1" items="${pros}">
	<!-- <s:iterator value="pros"> -->
	
		<div class="second_d_wai">
			<div class="img">
				<a href="<%=basePath%>/product.do?id=${c1.id}" target='_blank'>
					<img class="image" src="<%=basePath%>/productImages/${c1.productPic}" border="0" /> 
				</a>
			</div>
			<div class="shuming">
				<a href="<%=basePath%>/product.do?id=${c1.id}" target="_blank">${c1.productName}</a><a href="#" target="_blank"></a>
			</div>
			<div class="price">
				<span class="del">定价：￥${c1.fixedPrice}</span>
			</div>
			<div class="price">
				<span class="red">当当价：￥${c1.dangPrice}</span>
			</div>
		</div>
		<div class="book_c_xy_long"></div>
	<!-- </s:iterator> -->
	</c:forEach>
	<!--最新上架结束-->

</div>
<div class="clear"></div>