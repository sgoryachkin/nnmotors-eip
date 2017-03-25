<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" version="2.3">
	
	<jsp:directive.attribute name="listData" required="true" type="ru.nnmotors.eip.web.ui.component.paging.AbstractPagingListData"/>
	
	<div class="col-md-4">
		<c:out
			value="Показано ${fn:length(listData.items)} из ${listData.count}" />
	</div>
	<div class="col-md-8 text-right">
		<ul class="pagination">
			<c:forEach items="${listData.pages}" var="item">
				<li
					class="${item.active ? 'active' : ''} ${item.disable ? 'disabled' : ''}"><a
					href="${!item.disable ? pageContext.request.contextPath.concat('/user/list?page=').concat(item.page) : ''}"><c:out
							value="${item.name}" /></a></li>
			</c:forEach>
		</ul>
	</div>


</jsp:root>