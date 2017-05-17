<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<script type="text/javascript" src="<%=basePath%>/js/prototype-1.6.0.3.js">
		</script>
		<link href="<%=basePath%>/css/book.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/second.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('.goumaiButton').click(function(){
					var pid=$(this).children('.productId').val();
					$.post(
						"<%=basePath%>/buy.do",
						{'pid':pid},
						function(data){
							data=data.evalJSON();
							if(data.ok==true){
								$('#cartinfo'+pid).css('color','green').html('购买成功,已经加入购物车');
							}else{
								$('#cartinfo'+pid).css('color','red').html('已经购买过该商品');
							}
						}
					);
					return false;
				});
			});
			$(function(){
				$('#orderWay').change(function(){
					var way=$(this).val();
					if(way=="dang_price"){
						window.location="booklist.do?cid=${cid}&pid=${pid}&page=${page}&order=1";
					}else if(way=="moren"){
						window.location="booklist.do?cid=${cid}&pid=${pid}&page=${page}&order=2";
					}
				});
			});
			$(function(){
				$('#nextpage').click(function(){
					if($('#orderWay').val()=="dang_price"){
						$('#nextpage').attr('href',"booklist.do?cid=${cid}&pid=${pid}&page=${page+1}&order=1");
					}else if($('#orderWay').val()=="moren"){
						$('#nextpage').attr('href',"booklist.do?cid=${cid}&pid=${pid}&page=${page+1}&order=2");
					}else{
						$('#nextpage').attr('href',"booklist.do?cid=${cid}&pid=${pid}&page=${page+1}");
					}
				});
				$('#prevpage').click(function(){
					if($('#orderWay').val()=="dang_price"){
						$('#prevpage').attr('href',"booklist.do?cid=${cid}&pid=${pid}&page=${page-1}&order=1");
					}else if($('#orderWay').val()=="moren"){
						$('#prevpage').attr('href',"booklist.do?cid=${cid}&pid=${pid}&page=${page-1}&order=2");
					}else{
						$('#prevpage').attr('href',"booklist.do?cid=${cid}&pid=${pid}&page=${page-1}");
					}
				});
			});
		</script>
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="<%=basePath%>/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='<%=basePath %>/main/main.jsp'>当当图书</a> &gt;&gt;
			<font style='color: #cc3300'><strong>${parentName}</strong> </font>
		</div>

		<div class="book">
			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class="second_l_border2">
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
									<div class="second_fenlei">
										&middot;全部&nbsp;(${totalNum})
									</div>
								</div>
							</li>
							<div class="clear"></div>
							<c:forEach var="c1" items="${cats}">
							<!-- <s:iterator value="cats"> -->		
							<!--2级分类开始-->
							<li>
								<div>
									<div class="second_fenlei">
										&middot;
									</div>
									<div class="second_fenlei">
										<c:choose>  
										   <c:when test="${c1.id==cid}">
										   		<b><a href="booklist.do?cid=${c1.id}&pid=${pid}" style="color:red;">${c1.name}&nbsp;(${c1.pnum})</a></b>
										   </c:when>  
										   <c:otherwise>
										   		<a href="booklist.do?cid=${c1.id}&pid=${pid}">${c1.name}&nbsp;(${c1.pnum})</a>
										   </c:otherwise>  
										</c:choose> 
										<!-- 
										<s:if test="id==cid">
											<b><a href="booklist.do?cid=${id}&pid=${pid}" style="color:red;">${name}&nbsp;(${pnum})</a></b>
										</s:if>
										<s:else>
											<a href="booklist.?cid=${id}&pid=${pid}">${name}&nbsp;(${pnum})</a>
										</s:else>
										 -->
									</div>
								</div>
							</li>
						    <div class="clear"></div>
							<!--2级分类结束-->
							<!-- </s:iterator> -->	
							</c:forEach>	
							<li>
								<div></div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<select id="orderWay" name='select_order' size='1'
							class='list_r_title_ml'>
							<option value="pleaseSelect">
								--请选择排序方式--
							</option>
							<c:choose>  
							   <c:when test="${order==1}">
							   		<option value="dang_price" selected="selected">
										按当当价降序
									</option>
							   </c:when>  
							   <c:otherwise>
							   		<option value="dang_price">
										按当当价降序
									</option>
							   </c:otherwise> 
						   </c:choose>
						   <c:choose>  
							   <c:when test="${order==2}">
							   		<option value="moren" selected="selected">
										按默认方式排序
									</option>
							   </c:when>  
							   <c:otherwise>
							   		<option value="moren">
										按默认方式排序
									</option>
							   </c:otherwise>  
							</c:choose>
							<!-- 
							<s:if test="order==1">
								<option value="dang_price" selected="selected">
									按当当价降序
								</option>
							</s:if>
							<s:else>
								<option value="dang_price">
									按当当价降序
								</option>
							</s:else>
							<s:if test="order==2">
								<option value="moren" selected="selected">
									按默认方式排序
								</option>
							</s:if>
							<s:else>
								<option value="moren">
									按默认方式排序
								</option>
							</s:else>
							 -->
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

						<!--分页导航开始-->
						<c:choose>
							<c:when test="${page>1}">
						   		<div class='list_r_title_text3a'>
									<a name="link_page_next" id="prevpage">
									<img src='<%=basePath%>/images/page_up.gif' /> </a>
								</div>
						    </c:when>  
						    <c:otherwise>
						   		<div class='list_r_title_text3a'>
									<img src='<%=basePath%>/images/page_up_gray.gif' />
								</div>
						    </c:otherwise>
					    </c:choose>
						<!-- 
						<s:if test="page>1">
							<div class='list_r_title_text3a'>
								<a name="link_page_next" id="prevpage">
								<img src='<%=basePath%>/images/page_up.gif' /> </a>
							</div>
						</s:if>
						<s:else>
							<div class='list_r_title_text3a'>
								<img src='<%=basePath%>/images/page_up_gray.gif' />
							</div>
						</s:else>-->
						<div class='list_r_title_text3b'>
							<c:if test="${maxPage>0}">第${page}页/</c:if>共${maxPage}页
							<!-- <s:if test="maxPage>0">第${page}页/</s:if>共${maxPage}页 -->
						</div>
						<c:choose>
							<c:when test="${page<maxPage}">
						   		<div class='list_r_title_text3a'>
									<a name='link_page_next' id="nextpage">
										<img src='<%=basePath%>/images/page_down.gif' /> </a>
								</div>
						    </c:when>  
						    <c:otherwise>
						   		<div class='list_r_title_text3a'>
									<img src='<%=basePath%>/images/page_down_gray.gif' />
								</div>
						    </c:otherwise>
					    </c:choose>
					    <!-- 
						<s:if test="page<maxPage">	
							<div class='list_r_title_text3a'>
								<a name='link_page_next' id="nextpage">
									<img src='<%=basePath%>/images/page_down.gif' /> </a>
							</div>
						</s:if>
						<s:else>
							<div class='list_r_title_text3a'>
								<img src='<%=basePath%>/images/page_down_gray.gif' />
							</div>
						</s:else>
						 -->
						<!--分页导航结束-->
						</div>
					</div>
					
					<!--商品条目开始-->
					<c:forEach var="c1" items="${pros}">
					<!-- <s:iterator value="pros"> -->	
						<div class="list_r_line"></div>
						<div class="clear"></div>

						<div class="list_r_list">
							<span class="list_r_list_book">
								<a name="link_prd_img" href='<%=basePath%>/product.do?id=${c1.id}'>
									<img class="image" src="<%=basePath%>/productImages/${c1.productPic}" /> 
								</a>
							</span>
							<h2>
								<a name="link_prd_name" href='<%=basePath%>/product.do?id=${c1.id}'>${c1.productName}</a>
							</h2>
							<h3>
								顾客评分：100
							</h3>
							<h4 class="list_r_list_h4">
								作 者:
								<a href='#' name='作者'>${c1.author}</a>
							</h4>
							<h4>
								出版社：
								<a href='#' name='出版社'>${c1.publishing}</a>
							</h4>
							<h4>
								出版时间：<fmt:formatDate value="${c1.publishingDt}" pattern="yyyy年MM月dd日"/>
								<!-- <s:date name="publishingDt" format="yyyy年MM月dd日"/> -->
							</h4>
							<h5>
								这是一本好书，描述了Struts、Hibernate和Spring等框架的整合应用！
							</h5>
							<div class="clear"></div>
							<h6>
								<span class="del">￥${c1.fixedPrice}</span>
								<span class="red">￥${c1.dangPrice}</span>
								节省：￥${c1.fixedPrice-c1.dangPrice}
							</h6>
							<span class="list_r_list_button">
							<a class="goumaiButton" href="#"> 
								<input type="hidden" class="productId" value="${c1.id}"/>
								<img src='<%=basePath%>/images/buttom_goumai.gif' />
							</a>
							<span id="cartinfo${c1.id}"></span>
						</div>
						<div class="clear"></div>

					<!-- </s:iterator> -->
					</c:forEach>
					<!--商品条目结束-->

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
