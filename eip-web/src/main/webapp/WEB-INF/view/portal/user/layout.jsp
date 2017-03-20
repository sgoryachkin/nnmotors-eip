<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root
		xmlns:jsp="http://java.sun.com/JSP/Page"
		xmlns:sec="http://www.springframework.org/security/tags"
		xmlns:spring="http://www.springframework.org/tags"
		xmlns:form="http://www.springframework.org/tags/form"
		xmlns:c="http://java.sun.com/jsp/jstl/core"
		xmlns:tiles="http://tiles.apache.org/tags-tiles"
		version="2.3">


	<tiles:insertAttribute name="userHeader" />
	<tiles:insertAttribute name="userContent" />

</jsp:root>