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
        
        	<h2>Add Options Request</h2>
        	
   
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	
        	 <display:table name="list" requestURI="${listURL} " pagesize="5"  class="table" id="list">
        		
        		<display:column   format="{0,date,MM/dd/yyyy}"  sortable="true" property="dateModification" title="Assigment Date"> </display:column>
   	     		<display:column   format="{0,date,MM/dd/yyyy}"  sortable="true" property="dateAprovement" title="Aprovement Date"> </display:column>
   	     		
        		<display:column    property="category" title="Category"> </display:column>
        		<display:column    property="characteristic" title="Characteristic"> </display:column>
        		<display:column    property="newOptions" title="Options"> </display:column>
        		
        		
       			<display:column  title="Settings">
        			<c:set var="id" value="${list.id}" />
        		 	
        			
        			<c:if test = "${list.dateAprovement == null}">
        			<a href="/options/accept?id=${id}" onclick="selectobjID(${id})" class="btn btn-success"><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-ok-circle"></span></a>
        			</c:if>
        			<c:if test = "${list.dateAprovement != null}">
        			<a href="/options/accept?id=${id}" onclick="selectobjID(${id})" class="btn btn-success disabled"><span  class="pull-right hidden-xs showopacity  	glyphicon glyphicon-ok-circle"></span></a>
        			</c:if>
        			
        		</display:column>
        	</display:table>	
        		
      </div>
    </div>

</div>

<%@ include file="../common/footer.jspf"%>