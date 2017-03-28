<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:eip-form="urn:jsptagdir:/WEB-INF/tags/eip/form" version="2.3">

	<div>

		<form:form class="form-horizontal" method="post" modelAttribute="profileEditForm" enctype="multipart/form-data">

			<eip-form:input path="avatar" type="file" label="Aватар:" placeHolder="Выберите аватар" />
			<eip-form:input path="lastName" type="text" label="Фамилия:" placeHolder="Введите фамилию" />
			<eip-form:input path="firstName" type="text" label="Имя:" placeHolder="Введите имя" />
			<eip-form:input path="middleName" type="text" label="Отчество:" placeHolder="Введите отчество" />

			<eip-form:buttons>
				<input class="btn btn-primary" type="submit" value="Сохранить" />
				<input class="btn btn-default" type="reset" value="Сбросить" />
				<input class="btn btn-default" type="button" value="Отмена" />
			</eip-form:buttons>

		</form:form>


	</div>

</jsp:root>