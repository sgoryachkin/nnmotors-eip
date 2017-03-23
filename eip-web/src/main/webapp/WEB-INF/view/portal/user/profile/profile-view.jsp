<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.3">

	<div class="container-fluid">
		<div class="row">
			<div class="">
				<div class="well well-default">
					<div class="row">
						<div class="col-sm-6 col-md-4">
							<img
								src="${pageContext.request.contextPath}/attachment/download/${profile.avatarUrl}"
								alt="Фото" class="img-rounded img-responsive" />
						</div>
						<div class="col-sm-6 col-md-8">
							<h4>
								<c:out value="${profile.fullName}" />
							</h4>
							<p>
								<i class="glyphicon glyphicon-map-marker"></i> TODO://Адрес <br />
								<i class="glyphicon glyphicon-envelope"></i> TODO:// Электронная	почти <br /> 
								<i class="glyphicon glyphicon-globe"></i><a	href="http://www.jquery2dotnet.com">www.jquery2dotnet.com</a> <br />
								<i class="glyphicon glyphicon-gift"></i> TODO:// День рождения
							</p>
							<!-- Split button -->
							<div class="btn-group">
								<a
									href="${pageContext.request.contextPath}/user/${profile.id}/profile-edit"
									class="btn btn-default" role="button" aria-pressed="true">Редактировать</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</jsp:root>