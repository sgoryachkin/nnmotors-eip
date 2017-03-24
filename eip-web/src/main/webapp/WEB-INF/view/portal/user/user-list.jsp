<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.3">

	<div>
		<div>Filter</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>ФИО</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userListData.users}" var="item">
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
</jsp:root>