<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:util="http://java.sun.com/jsp/jstl/util"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" version="2.3">

	<jsp:directive.attribute name="path" required="true" />
	<jsp:directive.attribute name="label" required="true" />
	<jsp:directive.attribute name="type" required="true" />
	<jsp:directive.attribute name="placeHolder" required="true" />

	<spring:bind path="${path}">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:label class="control-label col-sm-2" path="lastName">${label}</form:label>
			<div class="col-sm-10">
				<form:input path="${path}" type="${type}" class="form-control"
					placeholder="${placeHolder}" />
				<form:errors class="help-block" path="${path}" />
			</div>
		</div>
	</spring:bind>

</jsp:root>