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
        	<h2>Characteristics</h2>
        	
        	<div class="alert alert-success">
				  <strong>${param.estade}</strong>
			</div>
        	
        	
        	 <div class="row">
			  <div class="col-sm-6"></div>
			  <div class="col-sm-4">
			  	<a href="/characteristics/add" class="btn btn-sm btn-primary btn-create" role="button">New Characteristic</a>
			  	
			  </div>
			  
			</div> 
        	
        	
        	<display:table name="list" requestURI="${listURL} " pagesize="5" class="table" id="list">
        		<display:column property="name" title="Name"> </display:column>
        		<display:column property="description" title="Description"></display:column>
        		<display:column property="typeInfo" title="Information Type"></display:column>
        		<display:column property="options" title="Options"></display:column>
        		<display:column  title="Settings">
        			<c:set var="id" value="${list.name}" />
        			<a href="/characteristics/update?id=${id}" onclick="selectobjID(${id})" class="btn btn-primary"><em class="fa fa-pencil"></em></a>
        			<a href="/characteristics/delete?id=${id}" onclick="selectobjID(${id})" class="btn btn-danger"><em class="fa fa-trash-o "></em></a> 
        		</display:column>
        	</display:table>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>