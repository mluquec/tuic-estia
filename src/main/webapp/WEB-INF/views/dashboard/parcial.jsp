<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>


<div class="row">
     <div class="col-sm-2">
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

    <div class="col-sm-5">
        	<h2>Tangible Interface to Moderate</h2>
        	<form:form method="post" commandName="tangible"  >
        	
        	<fieldset  class="form-group">
					<form:label path="general">General Information</form:label><br>
					<span>Please select the general information(basic, complementary)  you want to include</span><br>
					<form:select path="general"  items="${generalsMap}"    size="6" multiple="true"/>
					<form:errors path="general" cssClass="text-warning" />
				</fieldset>	
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	<h3>Basic Information</h3>
        	<c:set var = "index"  value = "${0}"/>
        	 <display:table name="${tangibleActual.basic}"   class="table" >
        	 
    			<display:column   property="name" title="Name"> </display:column>
    			<display:column   property="type" title="Information Type"> </display:column>
    			
    			<c:choose>
         			
		         <c:when test = "${tangibleActual.basic[index].type == 'LIST'}">
		             <display:column   title="Value"> 
		             	<display:table name="${ tangibleActual.basic[index].valueList}">
		             	</display:table>
		             </display:column>
		         </c:when>
		         <c:when test = "${tangibleActual.basic[index].type == 'IMG'}">
		          
		             
		             <display:column   title="Value"> 
		             	 <img class="img-responsive" src="${ tangibleActual.basic[index].value}"  width="230" height="175"> 
		             </display:column>
		           
		         </c:when>
		         <c:otherwise>
		        
		            <display:column   property="value" title="Value"> </display:column>
		         </c:otherwise>
		         

		      </c:choose>
	
    			
    			
    			<c:set var="index" value="${index+1}"/>
        	</display:table>
        	
      
        	<h3>Complementary Information</h3>
        	<c:set var = "index"  value = "${0}"/>
        	 <display:table name="${tangibleActual.complementary}"   class="table" >
    			<display:column   property="name" title="Name"> </display:column>
    			<display:column   property="type" title="Information Type"> </display:column>
    			<c:choose>
         			
		         <c:when test = "${tangibleActual.complementary[index].type == 'LIST'}">
		             <display:column   title="Value"> 
		             	<display:table name="${ tangibleActual.complementary[index].valueList}">
		             	</display:table>
		             </display:column>
		         </c:when>
		          <c:when test = "${tangibleActual.complementary[index].type == 'IMG'}">
		          
		             
		             <display:column   title="Value"> 
		             	 <img class="img-responsive" src="${ tangibleActual.complementary[index].value}"  width="230" height="175"> 
		             </display:column>
		           
		         </c:when>
		         <c:otherwise>
		        
		            <display:column   property="value" title="Value"> </display:column>
		         </c:otherwise>
		         

		      </c:choose>
	
    			
    			
    			<c:set var="index" value="${index+1}"/>
        	</display:table>
        	
        	<h3>Categories</h3>
        	
        	<fieldset  class="form-group">
				<span>Please select the category and the characteristic you wan to include</span><br>
				<form:select path="categories"  items="${categoriesMap}"    size="6" multiple="true"/>
				<form:errors path="categories" cssClass="text-warning" />
			</fieldset>
			
        	<c:set var = "index"  value = "${0}"/>
        	
        	<display:table name="${tangibleActual.categories}"  class="table" >
        		<display:column   property="name" title="Name"> </display:column>
        		
        		<display:column  title="Characteristics">
        		<c:set var = "indexC"  value = "${0}"/>
        		<display:table name="${tangibleActual.categories[index].characteristics}" class="table">
        				<display:column property="name" title="Name"> </display:column>
        				<display:column   property="type" title="Information Type"> </display:column>
        				
        				<c:choose>
         			
				         <c:when test = "${tangibleActual.categories[index].characteristics[indexC].type == 'LIST'}">
				             <display:column   title="Value"> 
				             	<display:table name="${ tangibleActual.categories[index].characteristics[indexC].valueList}">
				             	</display:table>
				             </display:column>
				         </c:when>
				         
				          <c:when test = "${tangibleActual.categories[index].characteristics[indexC].type == 'IMG'}">
		          
		             
				             <display:column   title="Value"> 
				             	 <img class="img-responsive" src="${ tangibleActual.categories[index].characteristics[indexC].value}"  width="230" height="175"> 
				             </display:column>
				           
				         </c:when>
				         <c:otherwise>
				        
				            <display:column   property="value" title="Value"> </display:column>
				         </c:otherwise>
				         
		
				      </c:choose>
	
    			
    			
    			<c:set var="indexC" value="${indexC+1}"/>
        				
        				
        				
        				
        				
        			</display:table> 
        		</display:column>
        		<c:set var="index" value="${index+1}"/>
        	</display:table>	
        	<button type="submit" class="btn btn-success">Submit</button>
			<a href="/dashboard/list" class="btn btn-info" role="button">Back</a>	
        	</form:form>	
      </div>

	<div class="col-sm-5">
        	<h2>Tangible Interface Publish</h2>
        	
        	
        	
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	<h3>Basic Information</h3>
        	<c:set var = "index"  value = "${0}"/>
        	 <display:table name="${tangiblePublish.basic}"   class="table" >
        	 
    			<display:column   property="name" title="Name"> </display:column>
    			<display:column   property="type" title="Information Type"> </display:column>
    			
    			<c:choose>
         			
		         <c:when test = "${tangiblePublish.basic[index].type == 'LIST'}">
		             <display:column   title="Value"> 
		             	<display:table name="${ tangiblePublish.basic[index].valueList}">
		             	</display:table>
		             </display:column>
		         </c:when>
		         <c:when test = "${tangiblePublish.basic[index].type == 'IMG'}">
		          
		             
		             <display:column   title="Value"> 
		             	 <img class="img-responsive" src="${ tangiblePublish.basic[index].value}"  width="230" height="175"> 
		             </display:column>
		           
		         </c:when>
		         <c:otherwise>
		        
		            <display:column   property="value" title="Value"> </display:column>
		         </c:otherwise>
		         

		      </c:choose>
	
    			
    			
    			<c:set var="index" value="${index+1}"/>
        	</display:table>
        	
      
        	<h3>Complementary Information</h3>
        	<c:set var = "index"  value = "${0}"/>
        	 <display:table name="${tangiblePublish.complementary}"   class="table" >
    			<display:column   property="name" title="Name"> </display:column>
    			<display:column   property="type" title="Information Type"> </display:column>
    			<c:choose>
         			
		         <c:when test = "${tangiblePublish.complementary[index].type == 'LIST'}">
		             <display:column   title="Value"> 
		             	<display:table name="${ tangiblePublish.complementary[index].valueList}">
		             	</display:table>
		             </display:column>
		         </c:when>
		          <c:when test = "${tangiblePublish.complementary[index].type == 'IMG'}">
		          
		             
		             <display:column   title="Value"> 
		             	 <img class="img-responsive" src="${ tangiblePublish.complementary[index].value}"  width="230" height="175"> 
		             </display:column>
		           
		         </c:when>
		         <c:otherwise>
		        
		            <display:column   property="value" title="Value"> </display:column>
		         </c:otherwise>
		         

		      </c:choose>
	
    			
    			
    			<c:set var="index" value="${index+1}"/>
        	</display:table>
        	
        	<h3>Categories</h3>
        	<c:set var = "index"  value = "${0}"/>
        	
        	<display:table name="${tangiblePublish.categories}"  class="table" >
        		<display:column   property="name" title="Name"> </display:column>
        		
        		<display:column  title="Characteristics">
        		<c:set var = "indexC"  value = "${0}"/>
        		<display:table name="${tangiblePublish.categories[index].characteristics}" class="table">
        				<display:column property="name" title="Name"> </display:column>
        				<display:column   property="type" title="Information Type"> </display:column>
        				
        				<c:choose>
         			
				         <c:when test = "${tangiblePublish.categories[index].characteristics[indexC].type == 'LIST'}">
				             <display:column   title="Value"> 
				             	<display:table name="${ tangiblePublish.categories[index].characteristics[indexC].valueList}">
				             	</display:table>
				             </display:column>
				         </c:when>
				         
				          <c:when test = "${tangiblePublish.categories[index].characteristics[indexC].type == 'IMG'}">
		          
		             
				             <display:column   title="Value"> 
				             	 <img class="img-responsive" src="${ tangiblePublish.categories[index].characteristics[indexC].value}"  width="230" height="175"> 
				             </display:column>
				           
				         </c:when>
				         <c:otherwise>
				        
				            <display:column   property="value" title="Value"> </display:column>
				         </c:otherwise>
				         
		
				      </c:choose>
	
    			
    			
    			<c:set var="indexC" value="${indexC+1}"/>
        				
        				
        				
        				
        				
        			</display:table> 
        		</display:column>
        		<c:set var="index" value="${index+1}"/>
        	</display:table>	
        		
        		
      </div>
    </div>


<%@ include file="../common/footer.jspf"%>