<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:eip-form="urn:jsptagdir:/WEB-INF/tags/eip/form" version="2.3">

	<div>

		<form:form class="form-horizontal" method="post"
			modelAttribute="profileEditForm" enctype="multipart/form-data">
			
			<eip-form:input path="avatar" type="file" label="Aватар:" placeHolder="Выберите аватар" />
			<eip-form:input path="lastName" type="text" label="Фамилия" placeHolder="Введите фамилию" />
			<eip-form:input path="firstName" type="text" label="Имя" placeHolder="Введите имя"/>
			<eip-form:input path="middleName" type="text" label="Отчество" placeHolder="Введите отчество"/>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input class="btn btn-default" type="submit" value="Сохранить" />
					<input class="btn btn-default" type="reset" value="Сбросить" /> 
					<input class="btn btn-link" value="Отмена" />
				</div>
			</div>

		</form:form>


	</div>

</jsp:root>