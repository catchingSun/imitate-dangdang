<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="<%=basePath %>/css/book.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>/css/second.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath %>/js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//异步加载左侧类别信息
				$('#left').load("<%=basePath%>/category.do");
				$('#recommend').load("<%=basePath%>/recommend.do");
				$('#hot').load("<%=basePath%>/hot.do");
				$('#new').load("<%=basePath%>/new.do");
				$('#newhot2').load("<%=basePath%>/newhot.do");
			});
		</script>
	</head>
	<body>
		&nbsp;
		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->
		<div style="width: 962px; margin: auto;">
			<a href="#" target="_blank"><img
					src="<%=basePath %>/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<img src="<%=basePath %>/images/window_loading.gif"/>正在加载
			</div>
			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--推荐图书开始-->
				<div class="second_c_border1" id="recommend">
					<!--<s:action name="recommend" namespace="/main" executeResult="true"></s:action>-->
				</div>
				<!--推荐图书结束-->

				<!--热销图书开始-->
				<div class="book_c_border2" id="hot">
					<!--<s:action name="hot" namespace="/main" executeResult="true"></s:action>-->
				</div>
				<!--热销图书结束-->

				<!--最新上架图书开始-->
				<div class="book_c_border2" id="new">
					<!--<s:action name="new" namespace="/main" executeResult="true"></s:action>-->
				</div>
				<!--最新上架图书结束-->

				<div class="clear">
				</div>
			</div>
			<!--中栏结束-->



			<!--右栏开始-->
			<div class="book_right" id="newhot2">
				<!--<s:action name="newhot" namespace="/main" executeResult="true"></s:action>-->
			</div>
			<!--右栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
