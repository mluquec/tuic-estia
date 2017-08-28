<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
        <div class="content"></div>
        	<h2>Select Moderator</h2>
        	
        	<h3> Interface </h3>
        	<strong>${param.id}</strong>
        	
        	<h3> User</h3>
        	<strong>${param.user}</strong> 
        	 <div class="row">
			  <div class="col-sm-5">
			  <a href="/users/list" class="btn btn-info" role="button">Back</a>
			  </div>
			  <div class="col-sm-5">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	<div></div>
        	<form:form method="post" commandName="modification"  >
        		
        		<form:input  style="display:none"   path="tangible.pk" value="${param.id }" type="text" class="form-control"/>
        		<fieldset  class="form-group">
					<form:label path="userModerator">Moderator</form:label>
					<div></div>
					<form:select path="userModerator"  items="${usersMap}"    size="6" multiple="true"/>
					<form:errors path="userModerator" cssClass="text-warning" />
				</fieldset>
        		<button type="submit" class="btn btn-success">Submit</button>
        	</form:form>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>