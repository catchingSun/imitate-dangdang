<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<=%basePath%>/js/jquery-1.4.min.js"></script>
<h2>
	编辑推荐
</h2>
<div id="__bianjituijian/danpin">
	<div class="second_c_02">
		<c:forEach var="c1" items="${pros}">
		<!-- <s:iterator value="pros"> -->
			<div class="second_c_02_b1">
				<div class="second_c_02_b1_1">
					<a href='<%=basePath%>/product.do?id=${c1.id}' target='_blank'>
						<img class="image" src="<%=basePath%>/productImages/${c1.productPic}" width="70" border="0" />
					</a>
				</div>
				<div class="second_c_02_b1_2">
					<h3>
						<a href='<%=basePath%>/product.do?id=${c1.id}' target='_blank' title='输赢'>${c1.productName}</a>
					</h3>
					<h4>
						作者：${c1.author} 著
						<br />
						出版社：${c1.publishing}&nbsp;&nbsp;&nbsp;&nbsp;
						出版时间:<fmt:formatDate value="${c1.publishingDt}" pattern="yyyy年MM月dd日"/><!-- <s:date name="publishingDt" format=""/> -->
					</h4>
					<h5>
						简介:${c1.description}
					</h5>
					<h6>
						<span class="del">定价：￥${c1.fixedPrice}&nbsp;&nbsp;</span>
						<span class="red">当当价：￥${c1.dangPrice}</span>
					</h6>
					<div class="line_xx"></div>
				</div>
			</div>
		<!-- </s:iterator> -->
		</c:forEach>
		
	</div>
</div>
