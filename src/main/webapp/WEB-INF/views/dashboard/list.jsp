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
        	<h2>Dashboard</h2>
        	
   
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	
        	 <display:table name="list" requestURI="${listURL} " pagesize="5"  class="table" id="list">
        		
        		<display:column   format="{0,date,MM/dd/yyyy}"  sortable="true" property="dateModification" title="Assigment Date"> </display:column>
        		<display:column   format="{0,date,MM/dd/yyyy}"  sortable="true" property="dateAprovement" title="Aprovement Date"> </display:column>
        		
        		<display:column   sortable="true" property="user" title="User"> </display:column>
        		<display:column   sortable="true" property="typeModification" title="Type"> </display:column>
        		<display:column    property="version" title="Version"> </display:column>
        		<display:column   sortable="true" property="stadeModification" title="Stade"> </display:column>
        		
        		<display:column  title="Basic Information">
        			<display:table name="${list.tangible.basic}" class="table">
        				<display:column property="name" title="Name"> </display:column>
        				
        				<display:column property="value" title="Value"> </display:column>
        			</display:table> 
        		</display:column>
        		<c:if test = "${list.dateAprovement == null}">
       			<display:column  title="Settings">
        			<c:set var="id" value="${list.tangible.pk}" />
        			<a href="/dashboard/view?id=${id}&user=${list.user}&version=${list.version}" onclick="selectobjID(${id})" class="btn btn-info" data-toggle="tooltip" data-placement="bottom" title="View" ><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-file"></span></a> 
        			<a href="/dashboard/accept?id=${id}&user=${list.user}&version=${list.version}&type=${list.typeModification}" onclick="selectobjID(${id})" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="Accept All"><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-ok-circle"></span></a>
        			<a href="/dashboard/deny?id=${id}&user=${list.user}&version=${list.version}&type=${list.typeModification}" onclick="selectobjID(${id})" class="btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Deny All"><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-remove-circle"></span></a>
        		    <a href="/dashboard/parcial?id=${id}&user=${list.user}&version=${list.version}&type=${list.typeModification}" onclick="selectobjID(${id})" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="Parcial Accept "><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-transfer"></span></a>
        			
        		</display:column>
        		</c:if>
        		<c:if test = "${list.dateAprovement != null}">
       			<display:column  title="Settings">
        			<c:set var="id" value="${list.tangible.pk}" />
        			<a href="/dashboard/view?id=${id}&user=${list.user}&version=${list.version}" onclick="selectobjID(${id})" class="btn btn-info " data-toggle="tooltip" data-placement="bottom" title="View" ><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-file"></span></a> 
        			<a href="/dashboard/accept?id=${id}&user=${list.user}&version=${list.version}&type=${list.typeModification}" onclick="selectobjID(${id})" class="btn btn-success disabled" data-toggle="tooltip" data-placement="bottom" title="Accept All"><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-ok-circle"></span></a>
        			<a href="/dashboard/deny?id=${id}&user=${list.user}&version=${list.version}&type=${list.typeModification}" onclick="selectobjID(${id})" class="btn btn-danger disabled" data-toggle="tooltip" data-placement="bottom" title="Deny All"><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-remove-circle"></span></a>
					<a href="/dashboard/parcial?id=${id}&user=${list.user}&version=${list.version}&type=${list.typeModification}" onclick="selectobjID(${id})" class="btn btn-success disabled" data-toggle="tooltip" data-placement="bottom" title="Parcial Accept "><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-transfer"></span></a>
					        		
        		</display:column>
        		</c:if>
        	</display:table>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>