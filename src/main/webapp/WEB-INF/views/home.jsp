<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <div class="inside">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
		<%@ include file="common/sidenavigation.jspf"%>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
		<%@ include file="common/sideuser.jspf"%>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_MODERATOR')">
		<%@ include file="common/sidemoderator.jspf"%>
		</sec:authorize>
      </div>
    </div>
    <div class="col-sm-3 col-md-9">
      <div class="inside">
        <div class="content"></div>
        	Language : <a href="/?locale=en"><img src="https://cdn3.iconfinder.com/data/icons/142-mini-country-flags-16x16px/32/flag-usa2x.png" /></a>|<a href="/?locale=fr"><img src="https://cdn3.iconfinder.com/data/icons/142-mini-country-flags-16x16px/32/flag-france2x.png" /></a>
        	<h1><spring:message code="title.message" /></h1>
        	<h2><spring:message code="subtitle.message" /></h2>	
        	<p><spring:message code="description.message" /></p>
        	<h2><spring:message code="objectives.message" /></h2>
        	<div class="row">
        		<div class="col-lg-4">
        			<h2><spring:message code="objectiveone.message" /> </h2>
        			<p><spring:message code="objectiveonedescription.message" /> </p>
        		</div>
        		<div class="col-lg-4">
        			<h2><spring:message code="objectivetwo.message" /> </h2>
        			<p><spring:message code="objectivetwodescription.message" /> </p>
        		</div>
        	</div>			
        		
      </div>
    </div>

</div>

<%@ include file="common/footer.jspf"%>
