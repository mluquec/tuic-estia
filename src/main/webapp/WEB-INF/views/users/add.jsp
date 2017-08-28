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
        	
        	<h2>User</h2>
        	        	
        	<div class="container">
        	<c:url var="formUrl"  value="/users/add" />
			<form:form method="post" commandName="user"  >
				
				<fieldset class="form-group">
					<form:label path="username">Username</form:label>
					<form:input path="username" type="text" class="form-control"
						required="required" />
					<form:errors path="username" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="password">Password</form:label>
					<form:label path="password">From 8 to 15 characters which contain at least one numeric digit, one uppercase, and one lowercase letter</form:label>
					<form:input path="password" type="text" class="form-control"
						required="required" onChange="CheckPassword(password)" />
					<form:errors path="password" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="firstName">First Name</form:label>
					<form:input path="firstName" type="text" class="form-control"
						required="required" />
					<form:errors path="firstName" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="lastName">Last Name</form:label>
					<form:input path="lastName" type="text" class="form-control"
						required="required" />
					<form:errors path="lastName" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="institution">Institution</form:label>
					<form:input path="institution" type="text" class="form-control"
						required="required" />
					<form:errors path="institution" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="email">Email</form:label>
					<form:input path="email" type="email" class="form-control"
						required="required" />
					<form:errors path="email" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="role">Role</form:label>
					<form:select path="role" items="${rolesMap}"/>
					<form:errors path="role" cssClass="text-warning" />
				</fieldset>
				<button type="submit" class="btn btn-success">Submit</button>
				<a href="/users/list" class="btn btn-info" role="button">Back</a>
			</form:form>
			</div>
        
        </div>
      </div>			     		
     </div>
</div>

<script src="<c:url value="../resources/static/js/general.js" />"></script>
<%@ include file="../common/footer.jspf"%>