<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:util="http://java.sun.com/jsp/jstl/util"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" version="2.3">

	<jsp:directive.attribute name="listData" required="true"
		type="ru.nnmotors.eip.web.ui.component.paging.AbstractPagingListData" />

	<div class="col-md-4">
		<c:out value="Показано ${fn:length(listData.items)} из ${listData.count}" />
	</div>
	<div class="col-md-8 text-right">
		<ul class="pagination">
			<c:forEach items="${listData.pages}" var="item">
				<spring:url var="linkUrl" value="" htmlEscape="true">
					<c:forEach items="${param}" var="entry">
						<c:if test="${entry.key != 'page'}">
							<spring:param name="${entry.key}" value="${entry.value}" />
						</c:if>
					</c:forEach>
					<spring:param name="page" value="${item.page}" />
				</spring:url>
				<li	class="${item.active ? 'active' : ''} ${item.disable ? 'disabled' : ''}">
					<c:choose>
						<c:when test="${!item.disable}">
							<a href="${linkUrl}"><c:out value="${item.name}" /></a>
						</c:when>
						<c:otherwise>
							<span><c:out value="${item.name}" /></span>
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
		</ul>
	</div>


</jsp:root>