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
        	<h2>Tangible Interfaces</h2>
        	
        	<div class="alert alert-success">
				  <strong>${param.estade}</strong>
			</div>
        	
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	
        	 <display:table name="list" requestURI="${listURL} " pagesize="5"  class="table" id="list">
        		<display:column   sortable="true" property="pk" title="Pk"> </display:column>
        		<display:column  title="Basic Information">
        			<display:table name="${list.basic}" class="table">
        				<display:column property="name" title="Name"> </display:column>
        				
        				<display:column property="value" title="Value"> </display:column>
        			</display:table> 
        		</display:column>
       			<display:column  title="Settings">
        			<c:set var="id" value="${list.pk}" />
        			<a href="/tangible/view?id=${id}" onclick="selectobjID(${id})" class="btn btn-info"><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-file"></span></a> 
        			
        		</display:column>
        	</display:table>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>