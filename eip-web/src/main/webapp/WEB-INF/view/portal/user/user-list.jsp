<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" 
	xmlns:eip="urn:jsptagdir:/WEB-INF/tags/eip" version="2.3">

	<div class="container-fluid">
		<div class="row">Filter</div>

		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ФИО</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userListData.items}" var="item">
						<tr>
							<td><a
								href="${pageContext.request.contextPath}/user/${item.id}/profile"><span
									class="glyphicon glyphicon-user" />&#160;<c:out
										value="${item.fullName}" /></a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		<div class="row">
			<eip:paging-tag listData="${userListData}"/>
		</div>
	</div>
</jsp:root>