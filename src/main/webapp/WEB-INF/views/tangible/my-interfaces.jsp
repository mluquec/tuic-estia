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
        	<h2>My Tangible Interfaces</h2>
        	
        	<div class="alert alert-success">
				  <strong>${param.estade}</strong>
			</div>
        	
        	
        	 <div class="row">
			  <div class="col-sm-5">
			
			  </div>
			  <div class="col-sm-5">
			  	<a href="/tangible/add" class="btn btn-sm btn-primary btn-create" role="button">New Interface</a>
			  	
			  	<a href="/tangible/my-request" class="btn btn-sm btn-primary btn-create" role="button">Request Moderator</a>
			  	
			  	<a href="/tangible/my-upload" class="btn btn-sm btn-primary btn-create" role="button">Upload Interfaces</a>
			  </div>
			  
			</div> 
        	
        	
        	 <display:table name="list" requestURI="${listURL} " pagesize="5"  class="table" id="list">
        		<display:column  format="{0,date,MM/dd/yyyy}"  sortable="true" property="dateModification" title="Modification Date"> </display:column>
        		<display:column  format="{0,date,MM/dd/yyyy}"  sortable="true" property="dateAprovement" title="Submit Date"> </display:column>
        		
        		<display:column  title="Basic Information">
        		<c:set var = "index"  value = "${0}"/>
        			<display:table name="${list.tangible.basic}" class="table">
        					
        				<display:column property="name" title="Name"> </display:column>
        				
        				<c:choose>
         			
				         <c:when test = "${list.tangible.basic[index].type == 'LIST'}">
				             <display:column   title="Value"> 
				             	<display:table name="${ list.tangible.basic[index].valueList}">
				             	</display:table>
				             </display:column>
				         </c:when>
				         
				         <c:otherwise>
				        
				            <display:column   property="value" title="Value"> </display:column>
				         </c:otherwise>
				         
		
				      </c:choose>
			
		    			
		    			
		    			<c:set var="index" value="${index+1}"/>
        			</display:table> 
        		</display:column>
        		
       			<display:column  title="Settings">
       			<c:set var="id" value="${list.tangible.pk}" />
       			<c:if test = "${list.dateAprovement == null}">
        			
        			<a href="/tangible/update?id=${id}" onclick="selectobjID(${id})" class="btn btn-primary" data-toggle="tooltip" data-placement="bottom" title="Edit" ><em class="fa fa-pencil"></em></a>
        			<a href="/tangible/delete?id=${id}" onclick="selectobjID(${id})" class="btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Delete" ><em class="fa fa-trash-o "></em></a> 
        			<a href="/tangible/my-interfaceview?id=${id}" onclick="selectobjID(${id})" class="btn btn-info" data-toggle="tooltip" data-placement="bottom" title="View"><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-file"></span></a> 
        			<a href="/tangible/review?id=${id}" onclick="selectobjID(${id})" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="Submit the interface" ><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-open"></span></a>
        		</c:if>
        		<c:if test = "${list.dateAprovement != null}">
        		<a href="/tangible/update?id=${id}" onclick="selectobjID(${id})" class="btn btn-primary disabled" data-toggle="tooltip" data-placement="bottom" title="Edit" ><em class="fa fa-pencil"></em></a>
        			<a href="/tangible/delete?id=${id}" onclick="selectobjID(${id})" class="btn btn-danger disabled" data-toggle="tooltip" data-placement="bottom" title="Delete" ><em class="fa fa-trash-o "></em></a> 
        			<a href="/tangible/my-interfaceview?id=${id}" onclick="selectobjID(${id})" class="btn btn-info"  data-toggle="tooltip" data-placement="bottom" title="View" ><span  class="pull-right hidden-xs showopacity glyphicon glyphicon-file"></span></a> 
        			<a href="/tangible/review?id=${id}" onclick="selectobjID(${id})" class="btn btn-success disabled"  data-toggle="tooltip" data-placement="bottom" title="Submit the interface" ><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-open"></span></a>
        		</c:if>
        		</display:column>
        		
        	</display:table>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>