<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="book_r_border2" id="__XinShuReMai">
	<div class="book_r_b2_1x" id="new_bang">
		<h2 class="t_xsrm">
			新书热卖榜
		</h2>
		<c:forEach var="c1" items="${pros}">
		<!-- <s:iterator value="pros"> -->
			<div id="NewProduct_1_o_t" onmouseover="">
				<h3 class="second">
					<a href="<%=basePath %>/product.do?id=${c1.id}" target="_blank">${c1.productName}</a>
				</h3>
			</div>
		<!-- </s:iterator> -->
		</c:forEach>
		<h3 class="second">
			<span class="dot_r"> </span><a href="#" target="_blank">更多&gt;&gt;</a>
		</h3>
	</div>
</div>