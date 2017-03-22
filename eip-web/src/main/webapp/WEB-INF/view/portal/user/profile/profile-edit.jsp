<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags" version="2.3">

	<div>

		<form:form class="form-horizontal" method="post" commandName="advertForm"
			action="${pageContext.request.contextPath}/user/${profile.id}/profile-edit-avatar"
			enctype="multipart/form-data">

			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="control-label col-sm-2">Аватар:</label>
				<div class="col-sm-10">
					<input id="avatar" type="file" class="form-control"
						placeholder="Укажите файл JPG, PNG" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input class="btn btn-default" type="submit" value="Обновить" /> <input
						class="btn btn-default" type="reset" value="Сбросить" />
				</div>
			</div>

		</form:form>

		<form:form class="form-horizontal" method="post"
			modelAttribute="profileEditForm">

			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:label class="control-label col-sm-2" path="lastName">Фамилия:</form:label>
					<div class="col-sm-10">
						<form:input id="lastName" path="lastName" type="text"
							class="form-control" placeholder="Введите фамилию" />
						<form:errors class="help-block" path="lastName" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:label class="control-label col-sm-2" path="firstName">Имя:</form:label>
					<div class="col-sm-10">
						<form:input path="firstName" type="text" class="form-control"
							placeholder="Введите имя" />
						<form:errors class="help-block" path="firstName" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="middleName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:label class="control-label col-sm-2" path="middleName">Отчество:</form:label>
					<div class="col-sm-10">
						<form:input id="middleName" path="middleName" type="text"
							class="form-control" placeholder="Введите отчество" />
						<form:errors class="help-block" path="middleName" />
					</div>
				</div>
			</spring:bind>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input class="btn btn-default" type="submit" value="Сохранить" />
					<input class="btn btn-default" type="reset" value="Сбросить" /> <input
						class="btn btn-link" value="Отмена" />
				</div>
			</div>

		</form:form>


	</div>

</jsp:root>