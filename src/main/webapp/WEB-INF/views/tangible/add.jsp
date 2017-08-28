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
      
       
        	
        	<h2>Tangible Interface</h2>
        	        	
        
        	
			<form:form method="post" commandName="tangible"  >
			
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
				      <c:set var = "index"  value = "${0}"/>
				      	<c:forEach var = "categoryNames" items="${ tangible.categories}">
					         <li><a class="nav-link" data-toggle="tab"   onclick="noSpaceRedirect( this, '${ tangible.categories[ index ].name}')" role="tab">${ tangible.categories[ index ].name}</a></li> 
					    	<c:set var="index" value="${index+1}"/>	
					    </c:forEach>
				                           
				      </ul>
				    </li>
			</ul>
			<c:set var = "index"  value = "${0}"/>
			
			<!-- Tab panes -->
				<div class="tab-content">
				
				  <div class="tab-pane active" id="basic" role="tabpanel">
				  	<c:forEach var = "basicIndex" items="${tangible.basic}">
					
							<fieldset class="form-group">
								<form:label path="basic[${index}].value">${ tangible.basic[ index ].name} 
									<a title="Description" data-toggle="popover" data-trigger="hover" data-content="${ tangible.basic[ index ].description} "><span class="glyphicon glyphicon-exclamation-sign"></span></a> 
								</form:label>
								<form:input style="display:none" path="basic[${index}].name" type="text" class="form-control"/>
								<form:input style="display:none" path="basic[${index}].type" type="text" class="form-control"/>
								<form:input style="display:none" path="basic[${index}].description" type="text" class="form-control"/>

								<c:choose>
								  <c:when test="${tangible.basic[ index ].type == 'BOOL'}">
								  	<div>
								    <form:radiobutton path="basic[${index}].value" value="true" label="True" />
           							<form:radiobutton path="basic[${index}].value" value="false" label="False" />
           							</div>
								  </c:when>
								  <c:when test="${tangible.basic[ index ].type == 'IMG'}">
								  	<div>
								     <form:input path="basic[${index}].value" type="text" class="form-control"/>
           							</div>
								  </c:when>
								  <c:when test="${tangible.basic[ index ].type == 'LIST'}">
								  <form:input  style="display:none" path="basic[${index}].options"   class="form-control" />
								  <div>
								  	 <form:label path="basic[${index}].newOptions"> Add Options </form:label>
									<form:input  path="basic[${index}].newOptions"   class="form-control" />
								  </div>
								 
								    <div style="overflow-y:scroll; height:100px;" >
								    	
								    	<ul class="checkbox-grid" >
							
								
								    	 <form:checkboxes element="li" items="${ tangible.basic[ index ].options}" path="basic[${index}].valueList"   />
								    	</ul>
								    </div> 
								  </c:when>
								  <c:otherwise>
								   <form:input path="basic[${index}].value" type="text" class="form-control"
									required="required" />
								  </c:otherwise>
								</c:choose>
								
								<form:errors path="basic[${index}].value" cssClass="text-warning" />
							</fieldset>
							<c:set var="index" value="${index+1}"/>
					 </c:forEach>
				  </div>
			
			<c:set var = "index"  value = "${0}"/>
			 
				  <div class="tab-pane" id="complementary" role="tabpanel">
				  			<c:forEach var = "complementaryIndex" items="${tangible.complementary}">
					
							<fieldset class="form-group">
								<form:label path="complementary[${index}].value">${ tangible.complementary[ index ].name} 
									<a title="Description" data-toggle="popover" data-trigger="hover" data-content="${ tangible.basic[ index ].description} "><span class="glyphicon glyphicon-exclamation-sign"></span></a> 
								</form:label>
								<form:input style="display:none" path="complementary[${index}].name" type="text" class="form-control"/>
								<form:input style="display:none" path="complementary[${index}].type" type="text" class="form-control"/>
								<form:input style="display:none" path="complementary[${index}].description" type="text" class="form-control"/>

								<c:choose>
								  <c:when test="${tangible.complementary[ index ].type == 'BOOL'}">
								  	<div>
								    <form:radiobutton path="complementary[${index}].value" value="true" label="True" />
           							<form:radiobutton path="complementary[${index}].value" value="false" label="False" />
           							</div>
								  </c:when>
								   <c:when test="${tangible.complementary[ index ].type == 'IMG'}">
								  	<div>
								     <form:input path="complementary[${index}].value" type="text" class="form-control"/>
           							</div>
								  </c:when>
								  <c:when test="${tangible.complementary[ index ].type == 'LIST'}">
								  <form:input style="display:none"  path="complementary[${index}].options"  value="${complementary[index].options}"  class="form-control"
									 />
									 <div>
								  	 <form:label path="complementary[${index}].newOptions"> Add Options </form:label>
									<form:input  path="complementary[${index}].newOptions"   class="form-control" />
								  </div>
								    <div style="overflow-y:scroll; height:100px;" >
								    	<ul class="checkbox-grid" >
								    	 <form:checkboxes element="li" items="${ tangible.complementary[ index ].options}" path="complementary[${index}].valueList" />
								    	</ul>
								    </div> 
								  </c:when>
								  <c:otherwise>
								   <form:input path="complementary[${index}].value" type="text" class="form-control"
									 />
								  </c:otherwise>
								</c:choose>
								
								<form:errors path="complementary[${index}].value" cssClass="text-warning" />
							</fieldset>
							<c:set var="index" value="${index+1}"/>
					 </c:forEach>
				  </div>
				  
			<c:set var = "indexCategory"  value = "${0}"/>
			
					<c:forEach var = "categoryNames" items="${ tangible.categories}">
					<c:set var = "index"  value = "${0}"/>		
					  <c:set var = "name1" value = "${ tangible.categories[ indexCategory].name}"/>
					  <c:set var = "name2" value =  "${fn:replace(name1, ' ', '')}"/>
						<div class="tab-pane" id="${name2}" role="tabpanel">
							<h2>${ tangible.categories[ indexCategory].name}</h2>
							<form:input style="display:none" path="categories[${indexCategory}].name" type="text" class="form-control"/>
							
							<c:forEach var = "complementaryIndex" items="${tangible.categories[ indexCategory].characteristics}">
					
							<fieldset class="form-group">
								<form:label path="categories[${indexCategory}].characteristics[${index}].value">${ tangible.categories[ indexCategory].characteristics[ index ].name} 
									<a title="Description" data-toggle="popover" data-trigger="hover" data-content="${ tangible.categories[ indexCategory].characteristics[ index ].description} "><span class="glyphicon glyphicon-exclamation-sign"></span></a> 
								</form:label>
								<form:input style="display:none" path="categories[${indexCategory}].characteristics[${index}].name" type="text" class="form-control"/>
								<form:input style="display:none" path="categories[${indexCategory}].characteristics[${index}].type" type="text" class="form-control"/>
								<form:input style="display:none" path="categories[${indexCategory}].characteristics[${index}].description" type="text" class="form-control"/>
								<c:choose>
								  <c:when test="${tangible.categories[ indexCategory].characteristics[ index ].type == 'BOOL'}">
								  	<div>
								    <form:radiobutton path="categories[${indexCategory}].characteristics[${index}].value" value="true" label="True" />
           							<form:radiobutton path="categories[${indexCategory}].characteristics[${index}].value" value="false" label="False" />
           							</div>
								  </c:when>
								  <c:when test="${tangible.categories[ indexCategory].characteristics[ index ].type == 'IMG'}">
								  	<div>
								    	<form:input path="categories[${indexCategory}].characteristics[${index}].value" type="text" class="form-control"/>
           							</div>
								  </c:when>
								  <c:when test="${tangible.categories[ indexCategory].characteristics[ index ].type == 'LIST'}">
								   <form:input style="display:none"  path="categories[${indexCategory}].characteristics[${index}].options"  value="${categories[indexCategory].characteristics[index].options}"  class="form-control"
									 /> 
								<div>
								  	 <form:label path="categories[${indexCategory}].characteristics[${index}].newOptions"> Add Options </form:label>
									<form:input  path="categories[${indexCategory}].characteristics[${index}].newOptions"   class="form-control" />
								  </div>
								    <div style="overflow-y:scroll; height:100px;" >
								    	<ul class="checkbox-grid" >
								    	 <form:checkboxes element="li" items="${ tangible.categories[ indexCategory].characteristics[ index ].options}" path="categories[${indexCategory}].characteristics[${index}].valueList" />
								    	</ul>
								    </div> 
								  </c:when>
								  <c:otherwise>
								   <form:input path="categories[${indexCategory}].characteristics[${index}].value" type="text" class="form-control"
									 />
									
								  </c:otherwise>
								</c:choose>
								
								<form:errors path="categories[${indexCategory}].characteristics[${index}].value" cssClass="text-warning" />
							</fieldset>
							<c:set var="index" value="${index+1}"/>
					 </c:forEach>
							
						</div>
					    	<c:set var="indexCategory" value="${indexCategory+1}"/>	
					 </c:forEach>
				
				</div>
			
			
			
			
			
			
			
			
			
				
				
				<button type="submit" class="btn btn-success">Submit</button>
				<a href="/tangible/my-interfaces" class="btn btn-info" role="button">Back</a>
			</form:form>
			</div>
        
        </div>
 			     		
  



<script src="<c:url value="../resources/static/js/general.js" />"></script>
<%@ include file="../common/footer.jspf"%>