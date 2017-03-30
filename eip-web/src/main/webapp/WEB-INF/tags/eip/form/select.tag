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
	<jsp:directive.attribute name="url" required="true" />
	
	<spring:bind path="${path}">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:label class="control-label col-md-2" path="lastName">${label}</form:label>
			<div class="col-md-10">
				<form:select path="${path}" class="form-control chosen-select">
				  <option value=""></option>
				</form:select>
				<form:errors class="help-block" path="${path}" />
			</div>
			<script type="text/javascript">
		<c:set var="request" value="${url}"/>
		
		var myChosen = $(".chosen-select").chosen();
		
		$(".chosen-search input").autocomplete({
		    source: function( request, response ) {
		        $search_param = $('.chosen-search input').val();
		        var data = {
		            filter: $search_param
		        };
		            $.get("${request}", data, function onAjaxSuccess(data) {
		                if((data.length!='0')) {
		                    $('ul.chosen-results').find('li').each(function () {
		                        $(this).remove();//отчищаем выпадающий список перед новым поиском
		                    });
		                    myChosen.find('option').each(function () {
		                        $(this).remove(); //отчищаем поля перед новым поисков
		                    });
		                };
		                data.forEach(function(opt) {
	                    	console.log(opt.name);
	                    	var option = document.createElement('option'); 
	                    	option.value = opt.id
	                    	option.innerHTML = opt.name;
	                    	myChosen.append(option);
	                    });
	                    myChosen.trigger("chosen:updated");
	                    //$('.chosen-search input').val($search_param);
	                    //anSelected = myChosen.val();
		            });
		    }

		})

	</script>
		</div>
	</spring:bind>
	
	

</jsp:root>