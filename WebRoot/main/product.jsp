<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<script type="text/javascript" src="<%=basePath%>/js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript" src="../js/prototype-1.6.0.3.js">
		</script>
		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="../css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
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
		</script>
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->
		<div class="book">
			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">
					
					<!--商品条目开始-->
						<div class="list_r_line"></div>
						<div class="clear"></div>

						<div class="list_r_list">
							<h3>
								丛书名:<a name="link_prd_name" href='#'>${pro.productName }</a>
							</h3>
							<div class="clear"></div>
							<a>
								<img class="image" src="../productImages/${pro.productPic }"  width="120" height="150"/>
							</a>
							<h3>
								顾客评分：100
							</h3>
							<h4>
								<ul>
								  <li>作 者:<a href='#' name='作者'>${pro.author}</a> </li>
								  <li>出版社：<a href='#' name='出版社'>${pro.publishing}</a> </li>
								  <li>出版时间：
								   <fmt:formatDate value="${pro.publishingDt}" pattern="yyyy年MM月dd日"/>
								   <!-- <s:date name="pro.publishingDt" format="yyyy年MM月dd日"/> --> 
								  </li>
								  <li>字　　数： ${pro.wordNumber } </li>
								  <li>版　　次： ${pro.whichEdtion } </li>
								  <li>页　　数： ${pro.totalPage } </li>
								  <li>印刷时间： ${pro.printTime } </li>
								  <li>开　　本： 16开 </li>
								  <li>印　　次： ${pro.printNumber } </li>
								  <li>纸　　张： 胶版纸 </li>
								  <li>I S B N ： ${pro.isbn } </li>
								  <li>包　　装： 平装 </li>
								  <li>
								  	<span class="del">￥${pro.fixedPrice}</span>
									<span class="red">￥${pro.dangPrice}</span>
									节省：￥${pro.fixedPrice-pro.dangPrice}
									<a class="goumaiButton" href="#"> 
										<input type="hidden" class="productId" value="${pro.id}"/>
										<img src='../images/buttom_goumai.gif' />
									</a>
									<span id="cartinfo${pro.id}"></span>
								  </li>
								</ul>
							</h4>
							
						</div>
						<div class="clear"></div>
					
						<!--商品条目结束-->

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>
				<div class="list_r_list">	
					<h2 style="font-size:20px;color:green">编辑推荐</h2>
					<div class="clear"></div>
					<div>
						在这本书里，我力图说明儿童早期教育中的某些问题，尤其要让读者认识到一个事实：我们的工作成就不仅仅是创建了一种新的教育模式。我们得出的结论已经显现于这本书名之中――发现孩子！<br/>
						――玛丽娅&#8226;蒙台梭利（本书作者）<br/><br/>
						今天，人类社会迫切需要重建教育方法；为此而奋斗，也就是为人类复兴而奋斗！<br/>
						　――塞吉（玛丽娅&#8226;蒙台梭利博士的老师）<br/><br/>
						蒙台梭利理论体系的精华是她对下面这个真理的有力论断或再论断：除非在自由的气氛中，儿童即不可能发展自己，也不可能受到有益的研究。<br/>
					</div>
					<h2 style="font-size:20px;color:green">内容简介</h2>
					<div class="clear"></div>
					<div>
						${pro.description }
					</div>
					<h2 style="font-size:20px;color:green">作者简介</h2>
					<div class="clear"></div>
					<div>
						${pro.author}${pro.authorSummary }
					</div>
					<h2 style="font-size:20px;color:green">目录</h2>
					<div class="clear"></div>
					<div>
						${pro.catalogue }
					</div>
					<h2 style="font-size:20px;color:green">媒体评论</h2>
					<div class="clear"></div>
					<div>
						好，非常畅销!难得的一本好书
					</div>
					<h2 style="font-size:20px;color:green">书摘插图</h2>
					<div class="clear"></div>
					<div>
						第2章 
						对教育方法的历史回顾<br/>　　儿童之家使用的教育体系实际上已经向前迈出了一大步。假如人们认为我和正常儿童相处所得的经验相对短暂，那么此经验建立在以往对非正常儿童的教育经验基础之上，因此也代表着一段相当长期的思想。<br/>　　
						假如我们想开发出科学的教育体系，就必须开辟出一条前所未有的新路。教师们必须接受专业培训，同时而学校也必须进行转变。假如教师们都接受了观察与实验的培训，那他们必须在学校里执行这些活动。<br/>
						　　因此，科学教育体系的基本需求是有一个能允许儿童自由发展个性的学校。如果某种教育体系是基于对学生个体的研究，那么其研究方式应该是对行动自由的儿童进行观察和研究，而不是对一个受压制的学生进行观察和研究。<br/>
						　　在人类学和实验心理学的帮助下，在一个人应试教育为主的学校里，开发新型教育方法，是最愚蠢的想法。<br/>
						　　每个领域的实验科学，都是由使用自己独特的方法中发展而来。细菌学起源于分离并培育微生物。犯罪学、医学和教育学也都分别在不同类型的个体上使用过最初的人体测量方法，如在罪犯上、精神病人、医院里的临床病人、学生身上等。实验心理学在开始阶段就要指出执行实验的精确技术。<br/>
						……
					</div>					
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
