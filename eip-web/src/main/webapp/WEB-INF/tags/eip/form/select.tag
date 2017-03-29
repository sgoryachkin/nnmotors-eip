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
			<form:label class="control-label col-md-2" path="lastName">${label}</form:label>
			<div class="col-md-10">
				<form:select path="${path}" type="${type}" class="form-control chosen-select" style=" "
					placeholder="${placeHolder}" >
				  <option value=""></option>
	              <option value="United States">United States</option>
	              <option value="United Kingdom">United Kingdom</option>
				</form:select>
				<form:errors class="help-block" path="${path}" />
			</div>
		</div>
	</spring:bind>
	
	<script type="text/javascript">
		$(".chosen-select").chosen();
		$(".chosen-search input").autocomplete({
		    source: function( request, response ) {
		        $search_param = $('.chosen-search input').val();
		        var data = {
		            search_param: $search_param
		        };
		        if($search_param.length>3) { //отправлять поисковой запрос к базе, если введено более трёх символов
		            $.post('my_search.php', data, function onAjaxSuccess(data) {
		                if((data.length!='0')) {
		                    $('ul.chosen-results').find('li').each(function () {
		                        $(this).remove();//отчищаем выпадающий список перед новым поиском
		                    });
		                    $('select').find('option').each(function () {
		                        $(this).remove(); //отчищаем поля перед новым поисков
		                    });
		                }
		                    for (var id in data) {
		                    	$(".chosen-select").append('<option value="' + data[id] + '">' + data['name'] + ' ' + data['last_name'] + ' ( ' + data['email'] + ' )</option>');
		                    }
		                    $(".chosen-select").trigger("chosen:updated");
		                    $('.chosen-search input').val($search_param);
		                    anSelected = $(".chosen-select").val();
		            });
		        }
		    }

		})
	</script>

</jsp:root>