<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>


<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <div class="inside">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
		<%@ include file="../common/sidenavigation.jspf"%>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
		<%@ include file="../common/sideuser.jspf"%>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_MODERATOR')">
		<%@ include file="../common/sidemoderator.jspf"%>
		</sec:authorize>
      </div>
    </div>
    <div class="col-sm-3 col-md-9">
      <div class="inside">
        <div class="content">
        	
        	<h2>Form</h2>
        	        	
        	<div class="container">
        	
			<form:form method="post" commandName="form"  >
				
				<fieldset class="form-group">
					<form:label path="name">Name</form:label>
					<form:input path="name" type="text" class="form-control"
						required="required" />
					<form:errors path="name" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="description">Description</form:label>
					<form:input path="description" var ="a" type="text" class="form-control" />
					<form:errors path="description" cssClass="text-warning" />
				</fieldset>
				<fieldset  class="form-group">
					<form:label path="general">General Information</form:label>
					<form:select path="general"  items="${generalsMap}"    size="6" multiple="true"/>
					<form:errors path="general" cssClass="text-warning" />
				</fieldset>	
				<fieldset  class="form-group">
					<form:label path="pk">Key</form:label>
					<form:select path="pk"  items="${generalsMap}"    size="6" />
					<form:errors path="pk" cssClass="text-warning" />
				</fieldset>
				<fieldset  class="form-group">
					<form:label path="categories">Categories</form:label>
					<form:select path="categories"  items="${categoriesMap}"    size="6" multiple="true"/>
					<form:errors path="categories" cssClass="text-warning" />
				</fieldset>
				
				
				<button type="submit" class="btn btn-success">Submit</button>
				<a href="/forms/list" class="btn btn-info" role="button">Back</a>
			</form:form>
			</div>
        
        </div>
      </div>			     		
     </div>
</div>

<script src="<c:url value="../resources/static/js/general.js" />"></script>
<%@ include file="../common/footer.jspf"%>