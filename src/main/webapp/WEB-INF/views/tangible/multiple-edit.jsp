<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
    <div class="col-sm-2 ">
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
    <div class="col-sm-9">
      
       
        	
        	<h2>Multiple Edit</h2>
        	        	
        
        	<h3>Tangible Interfaces</h3>
        	<span>Select the tangible interfaces to edit</span>
        	
			<form:form method="post" commandName="tangible"  >
			
				<fieldset  class="form-group">
					<form:label path="objectSelect">Interfaces</form:label>
					<div></div>
					<form:select path="objectSelect"  items="${tangible.objectSelect}"    size="6" multiple="true"/>
					<form:errors path="objectSelect" cssClass="text-warning" />
				</fieldset>
				
				<button type="submit" class="btn btn-success">Submit</button>
				<a href="/tangible/my-interfaces" class="btn btn-info" role="button">Back</a>
			</form:form>
			</div>
        
        </div>
 			     		
  



<script src="<c:url value="../resources/static/js/general.js" />"></script>
<%@ include file="../common/footer.jspf"%>