<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.3">

	<div>
		<div>
			<img alt="Фото" src="${pageContext.request.contextPath}/attachment/download/${profile.avatarUrl}"/>
		</div>

		<div class="form-horizontal">
		
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="control-label col-sm-2" path="lastName">ФИО:</div>
				<div class="col-sm-10">
					<div class="form-control">
						<c:out value="${profile.fullName}" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="${pageContext.request.contextPath}/user/${profile.id}/profile-edit"
						class="btn btn-default" role="button" aria-pressed="true">Редактировать</a>
				</div>
			</div>
		</div>

	</div>

</jsp:root>