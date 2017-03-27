<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags" version="2.3">

	<div>

		<form:form class="form-horizontal" method="post"
			modelAttribute="profileEditForm" enctype="multipart/form-data">

			<spring:bind path="avatar">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:label class="control-label col-sm-2" path="avatar">Выберите аватар:</form:label>
					<div class="col-sm-10">
						<form:input path="avatar" type="file"
							class="form-control input-default" />
						<form:errors class="help-block" path="avatar" />
					</div>
				</div>
			</spring:bind>

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