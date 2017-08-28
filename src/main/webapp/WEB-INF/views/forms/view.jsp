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
        	
        	<h2>Form</h2>
        	<a href="/forms/list" class="btn btn-info" role="button">Back</a>        	
        	<div class="container">
        	
			<h3>Name:</h3> <p>${form.name}</p>
			
			<h3>Description:</h3> <span>${form.description}</span>
			
			<h3>View Form</h3>
			<div class="container">
			  
			  <!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
				  <li class="nav-item">
				    <a class="nav-link active" data-toggle="tab" href="#basic" role="tab">Basic Information</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" data-toggle="tab" href="#complementary" role="tab">Complementary Information</a>
				  </li>
				 
				  <li class="dropdown">
				      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Categories<span class="caret"></span></a>
				      <ul class="dropdown-menu">
				      	<c:forEach var = "categoryNames" items="${categoryNames}">
					         <li><a class="nav-link" data-toggle="tab" href="#${categoryNames}" role="tab"><c:out value = "${categoryNames}"/></a></li> 
					    </c:forEach>
				                           
				      </ul>
				    </li>
				</ul>
				
				<!-- Tab panes -->
				<div class="tab-content">
				  <div class="tab-pane active" id="basic" role="tabpanel">
				  		<display:table name="basic" requestURI="${basicURL} " pagesize="5"  class="table">     	
						     <display:column   sortable="true" property="name" title="Name"> </display:column>
						     <display:column   property="description" title="Description"> </display:column>
						     <display:column   sortable="true" property="typeInfo" title="Information Type"> </display:column>
							 <display:column   property="options" title="Options"> </display:column>
						</display:table>	
				  </div>
				  <div class="tab-pane" id="complementary" role="tabpanel">
				  		<display:table name="complementary" requestURI="${complementaryURL} " pagesize="5"  class="table">     	
						     <display:column   sortable="true" property="name" title="Name"> </display:column>
						     <display:column   property="description" title="Description"> </display:column>
						     <display:column   sortable="true" property="typeInfo" title="Information Type"> </display:column>
							 <display:column   property="options" title="Options"> </display:column>
						</display:table>	
				  </div>
				   <c:forEach var = "categoryNames" items="${categoryNames}">
				  	
					<div class="tab-pane" id="${categoryNames}" role="tabpanel">
					
					<h3>${categoryNames}</h3>
					
					         	<c:forEach var = "characteristicsNames" items="${characteristics[categoryNames]}">
					         		<h4>${characteristicsNames.name}</h4>
					         		<ul>
					         			<li>Description: ${characteristicsNames.description}</li>
					         			<li>Information Type: ${characteristicsNames.typeInfo}</li>
					         			<c:set var = "typeInfo"  value = "${characteristicsNames.typeInfo}"/>
					         			<c:if test = "${ typeInfo == 'LIST'}">
									         <li>Options: ${characteristicsNames.options}</li>
									     </c:if>
					         		</ul>
					         		
					         	</c:forEach>
					</div>
					         
				  </c:forEach>
				 
				</div>
				
				
			</div>
			
			
			
			
			
			</div>
        
        </div>
      </div>			     		
     </div>
</div>

<script src="<c:url value="../resources/static/js/general.js" />"></script>
<%@ include file="../common/footer.jspf"%>