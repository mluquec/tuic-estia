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
        	<h2>Forms</h2>
        	
        	<div class="alert alert-success">
				  <strong>${param.estade}</strong>
			</div>
        	
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	<a href="/forms/add" class="btn btn-sm btn-primary btn-create" role="button">New Form</a>
			  	
			  </div>
			  
			</div> 
        	
        	
        	<display:table name="list" requestURI="${listURL} " pagesize="5"  class="table" id="list">
        		<display:column   sortable="true" property="name" title="Name"> </display:column>
        		<display:column property="isActive" title="Active"> </display:column>
        		<display:column property="description" title="Description"></display:column>
        		<display:column property="pk" title="Key"> </display:column>
        		<display:column  title="General Information">
        			<display:table name="${list.general}" >
        			</display:table> 
        		</display:column>
        		<display:column  title="Categories">
        			<display:table name="${list.categories}" >
        			</display:table> 
        		</display:column>
        		
        		<display:column  title="Settings">
        			<c:set var="id" value="${list.name}" />
        			<a href="/forms/update?id=${id}" onclick="selectobjID(${id})" class="btn btn-primary"><em class="fa fa-pencil"></em></a>
        			<a href="/forms/delete?id=${id}" onclick="selectobjID(${id})" class="btn btn-danger"><em class="fa fa-trash-o "></em></a> 
        			<a href="/forms/active?id=${id}" onclick="selectobjID(${id})" class="btn btn-primary"><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-star"></span></a> 
        			<a href="/forms/view?id=${id}" onclick="selectobjID(${id})" class="btn btn-info"><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-file"></span></a> 
        			
        		</display:column>
        	</display:table>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>