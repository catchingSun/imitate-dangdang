<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>
			分类浏览
		</h2>
		<c:forEach var="c1" items="${cats}">
   			<!-- Item <c:out value="${c1}"/><p> -->
   			<!--1级分类开始-->
			<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">
				<h3>
					[<a href='#'>${c1.name}</a>]
				</h3>
				
				<ul class="ul_left_list">
					<!--2级分类开始-->
					<c:forEach var="c2" items="${c1.subCats}">
					<!-- <s:iterator value="#c1.subCats" var="c2"> -->
						<li>
							<a href='booklist.do?cid=${c2.id}&pid=${c1.id}'>${c2.name}</a>
						</li>
					<!-- </s:iterator> -->
					</c:forEach>
					<!--2级分类结束-->
				</ul>
				
				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
			<!--1级分类结束-->
   			
   			
   			
		</c:forEach>
		
		
		<div class="bg_old">
			<h3>
				&nbsp;
			</h3>
		</div>
	</div>
</div>
