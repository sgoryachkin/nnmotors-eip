<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.3">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4">
				<img
					src="${pageContext.request.contextPath}/attachment/download/${profile.avatarUrl}"
					alt="Фото" class="img-rounded img-responsive" />
			</div>
			<div class="col-md-8">
				<h4>
					<c:out value="${profile.fullName}" />
				</h4>
				<p>
					<i class="glyphicon glyphicon-map-marker"></i> Мицубиши на Гагарина
					<br /> <i class="glyphicon glyphicon-envelope"></i>&#160;
					<c:out value="${profile.email}" />
					<br /> <i class="glyphicon glyphicon-earphone"></i>&#160;
					<c:out value="${profile.workPhone}" />
					<br /> <i class="glyphicon glyphicon-gift"></i> 02.05.1985
				</p>
				
				<div class="btn-group">
					<a
						href="${pageContext.request.contextPath}/user/${profile.id}/profile-edit"
						class="btn btn-default" role="button" aria-pressed="true">Редактировать</a>
				</div>
			</div>
		</div>
	</div>


</jsp:root>