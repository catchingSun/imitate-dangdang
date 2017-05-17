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
		<link href="<%=basePath%>/css/book.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/second.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/shopping_vehicle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//除掉字符串两端的空格
				function strip(str){
					var reg = /(^\s*)|(\s*$)/g;
					return str.replace(reg,'');
				}
				
				$('.updateNum').click(function(){
					var qty=$(this).prev().val();
					var id=$(this).parent().next('.productId').val();
					//非空检测
					if(strip(qty).length ==0){
						alert("数量不能为空");
						return;
					}
					//非数字检测
					var reg = /^[-]{0,1}[0-9]+$/;
					if(!reg.test(strip(qty))){
						alert('必须为数字');
						return;
					}
					if(strip(qty)<1){
						alert('必须为正数');
						return;
					}
					$(this).attr('href','<%=basePath%>/update.do?id=' + id + '&qty=' + strip(qty));
				});
				$('.deletePro').click(function(){
					var pid=$(this).parent().prev('.productId').val();
					$(this).attr('href','<%=basePath%>/delete.do?pid='+pid);
				});
				$('.recovery').click(function(){
					var pid=$(this).children('.productId').val();
					$(this).attr('href','<%=basePath%>/recovery.do?pid='+pid);
				});
			});
		</script>
	
	</head>
	<body>
		<br />
		<br />
		<div class="my_shopping">
			<img class="pic_shop" src="<%=basePath %>/images/pic_myshopping.gif" />

		</div>
		<div id="div_choice" class="choice_merch">
			<c:if test="${buyProsSize}!=0">
				<h2 id="cart_tips">
					您已选购以下商品
				</h2>
			</c:if>
			<div class="choice_bord">
				<table class="tabl_buy" id="tbCartItemsNormal">
					<tr class="tabl_buy_title">
						<td class="buy_td_6">
							<span>&nbsp;</span>
						</td>
						<td>
							<span class="span_w1">商品名</span>
						</td>
						<td class="buy_td_5">
							<span class="span_w2">市场价</span>
						</td>
						<td class="buy_td_4">
							<span class="span_w3">当当价</span>
						</td>
						<td class="buy_td_1">
							<span class="span_w2">数量</span>
						</td>
						<td class="buy_td_2">
							<span>变更数量</span>
						</td>
						<td class="buy_td_1">
							<span>删除</span>
						</td>
					</tr>
					<tr class='objhide' over="no">
						<td colspan="8">
							&nbsp;
						</td>
					</tr>
                      <!-- 购物列表开始 -->
                    <c:forEach var="c" items="${buyPros}">
					<!-- <s:iterator value="buyPros" var="c"> -->
						<tr class='td_no_bord'>
							<td style='display: none'>
								
							</td>
							<td class="buy_td_6">
								<span class="objhide"><img /> </span>
							</td>
							<td>
								<a href="#">${c.pro.productName}</a>
							</td>
							<td class="buy_td_5">
								<span class="c_gray">￥${c.pro.fixedPrice}</span>
							</td>
							<td class="buy_td_4">
								&nbsp;&nbsp;
								<span>￥${c.pro.dangPrice}</span>
							</td>
							<td class="buy_td_1">
								&nbsp;&nbsp;${c.qty}
							</td>

							<td >
								<input class="del_num" type="text" size="3" maxlength="4"/>
								<a href="#" class="updateNum">变更</a>
							</td>
							<input type="hidden" class="productId" value="${c.pro.id}"/>
							<td>
								<a href="#" class="deletePro">删除</a>
							</td>
						</tr>
					<!-- </s:iterator> -->	
					</c:forEach>
					<!-- 购物列表结束 -->
				</table>
				<c:choose>
					<c:when test="${buyProsSize}==0">
						还没有挑选商品<a href='<%=basePath %>/main/main.jsp'> 去挑选商品>></a>
						<div id="div_no_choice" class="objhide">
							<div class="choice_title"></div>
							<div class="no_select">
								您还没有挑选商品
							</div>
						</div>
				    </c:when>  
				    <c:otherwise>
				    	<div class="choice_balance">
							<div class="select_merch">
								<a href='<%=basePath %>/main/main.jsp'> 继续挑选商品>></a>
							</div>
							<div class="total_balance">
								<div class="save_total">
									您共节省：
									<span class="c_red"> ￥<span id="total_economy">${saving}</span>
									</span>
									<span id='total_vip_economy' class='objhide'> ( 其中享有优惠： <span
										class="c_red"> ￥<span id='span_vip_economy'>0.00</span> </span>
										) </span>
									<span style="font-size: 14px">|</span>
									<span class="t_add">商品金额总计：</span>
									<span class="c_red_b"> ￥<span id='total_account'>${totalCost}</span>
									</span>
								</div>
								<div id="balance" class="balance">
									<a name='checkout' href='<%=basePath %>/orderConfirm.do' > 
										<img src="<%=basePath%>/images/butt_balance.gif" alt="结算" border="0" title="结算" />
									</a>
								</div>
							</div>
						</div>
				    </c:otherwise>
			    </c:choose>
			    <!-- 
				<s:if test="buyPros.size()==0">
					还没有挑选商品<a href='../main/main.jsp'> 去挑选商品>></a>
					<div id="div_no_choice" class="objhide">
						<div class="choice_title"></div>
						<div class="no_select">
							您还没有挑选商品
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="choice_balance">
						<div class="select_merch">
							<a href='../main/main.jsp'> 继续挑选商品>></a>
						</div>
						<div class="total_balance">
							<div class="save_total">
								您共节省：
								<span class="c_red"> ￥<span id="total_economy">${saving}</span>
								</span>
								<span id='total_vip_economy' class='objhide'> ( 其中享有优惠： <span
									class="c_red"> ￥<span id='span_vip_economy'>0.00</span> </span>
									) </span>
								<span style="font-size: 14px">|</span>
								<span class="t_add">商品金额总计：</span>
								<span class="c_red_b"> ￥<span id='total_account'>${totalCost}</span>
								</span>
							</div>
							<div id="balance" class="balance">
								<a name='checkout' href='/dangdang/order/orderConfirm.action' > 
									<img src="../images/butt_balance.gif" alt="结算" border="0" title="结算" />
								</a>
							</div>
						</div>
					</div>
				</s:else>
				-->
			</div>
		</div>

		<!-- 用户删除恢复区 -->


		<div id="divCartItemsRemoved" class="del_merch">
			<div class="del_title">
				您已删除以下商品，如果想重新购买，请点击“恢复”
			</div>
			<table class=tabl_del id=del_table>
				<tbody>
				<c:forEach var="d" items="${delPros}">
				<!-- <s:iterator value="delPros" var="d"> -->
					<tr>
						<td width="58" class="buy_td_6">
							&nbsp;
						</td>
						<td width="365" class="t2">
							<a href="#">${d.pro.productName}</a>
						</td>
						<td width="106" class="buy_td_5">
							￥${d.pro.fixedPrice}
						</td>
						<td width="134" class="buy_td_4">
							<span>￥${d.pro.dangPrice}</span>
						</td>
						<td width="56" class="buy_td_1">
							<a class="recovery" href="#">恢复<input type="hidden" class="productId" value="${d.pro.id}"/></a>
						</td>
						<td width="16" class="objhide">
							&nbsp;
						</td>
					</tr>
				<!-- </s:iterator> -->
				</c:forEach>

					<tr class="td_add_bord">
						<td colspan=8>
							&nbsp;
						</td>
					</tr>

				</tbody>
			</table>
		</div>
		<br />
		<br />
		<br />
		<br />
		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>



