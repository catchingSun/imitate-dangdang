<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="<%=request.getContextPath() %>/css/book_head090107.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.4.min.js"></script>
<div class="head_container">
	<div class="head_welcome">
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span>
			<span class="head_welcome_text">
				<span class="little_n">
					| <a href="#" name="mydangdang" class="head_black12a">我的当当</a>
					|<a href="#" name="helpcenter" class="head_black12a" target="_blank">帮助</a>
				</span>
			</span>
			<div class="cart gray4012">
				<a href="<%=request.getContextPath() %>/cartlist.do">购物车</a>
			</div>
		</div>
		<span class="head_toutext" id="logininfo">
		<b><!-- 您好,<s:property value="#session.user.nickname"/>&nbsp;欢迎光临当当网 -->
		        您好,<c:out value="${user.nickname}"></c:out>&nbsp;欢迎光临当当网
		</b>
		<c:choose>  
		   <c:when test="${user==null}">
		   		[&nbsp;<a href="<%=request.getContextPath() %>/user/login_form.jsp" class="b">登录</a>|<a href="<%=request.getContextPath() %>/user/register_form.jsp" class="b">注册</a>&nbsp;]
		   </c:when>  
		   <c:otherwise>
		   		[&nbsp;<a href="<%=request.getContextPath() %>/exit.do" class="b" id="exit">登出</a>&nbsp;]
		   </c:otherwise>  
		</c:choose> 
		<c:if test="${user}==null">
			
		</c:if>
		<!-- 
		<s:if test="#session.user==null">
			[&nbsp;<a href="<%=request.getContextPath() %>/user/login_form.jsp" class="b">登录</a>|<a href="<%=request.getContextPath() %>/user/register_form.jsp" class="b">注册</a>&nbsp;]
		</s:if> 
		<s:else>[&nbsp;<a href="<%=request.getContextPath() %>user/exit.do" class="b" id="exit">登出</a>&nbsp;]</s:else>
		-->
		</span>
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a href="#" name="backtobook"><img
						src="<%=request.getContextPath() %>/images/booksaleimg/book_logo.gif" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b">
			</div>
		</div>
	</div>
	<div class="head_search_div">

	</div>
	<div class="head_search_bg"></div>
</div>
