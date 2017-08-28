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
        	<h2>Tangible Interface</h2>
        	
        	
        	
        	
        	 <div class="row">
			  <div class="col-sm-7"></div>
			  <div class="col-sm-3">
			  	
			  	
			  </div>
			  
			</div> 
        	
        	<h3>Basic Information</h3>
        	<c:set var = "index"  value = "${0}"/>
        	 <display:table name="${tangible.basic}"   class="table" >
        	 
    			<display:column   property="name" title="Name"> </display:column>
    			<display:column   property="type" title="Information Type"> </display:column>
    			
    			<c:choose>
         			
		         <c:when test = "${tangible.basic[index].type == 'LIST'}">
		             <display:column   title="Value"> 
		             	<display:table name="${ tangible.basic[index].valueList}">
		             	</display:table>
		             </display:column>
		         </c:when>
		         <c:when test = "${tangible.basic[index].type == 'IMG'}">
		          
		             
		             <display:column   title="Value"> 
		             	 <img class="img-responsive" src="${ tangible.basic[index].value}"  width="460" height="345"> 
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
        	 <display:table name="${tangible.complementary}"   class="table" >
    			<display:column   property="name" title="Name"> </display:column>
    			<display:column   property="type" title="Information Type"> </display:column>
    			<c:choose>
         			
		         <c:when test = "${tangible.complementary[index].type == 'LIST'}">
		             <display:column   title="Value"> 
		             	<display:table name="${ tangible.complementary[index].valueList}">
		             	</display:table>
		             </display:column>
		         </c:when>
		          <c:when test = "${tangible.complementary[index].type == 'IMG'}">
		          
		             
		             <display:column   title="Value"> 
		             	 <img class="img-responsive" src="${ tangible.complementary[index].value}"  width="460" height="345"> 
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
        	
        	<display:table name="${tangible.categories}"  class="table" >
        		<display:column   property="name" title="Name"> </display:column>
        		
        		<display:column  title="Characteristics">
        		<c:set var = "indexC"  value = "${0}"/>
        		<display:table name="${tangible.categories[index].characteristics}" class="table">
        				<display:column property="name" title="Name"> </display:column>
        				<display:column   property="type" title="Information Type"> </display:column>
        				
        				<c:choose>
         			
				         <c:when test = "${tangible.categories[index].characteristics[indexC].type == 'LIST'}">
				             <display:column   title="Value"> 
				             	<display:table name="${ tangible.categories[index].characteristics[indexC].valueList}">
				             	</display:table>
				             </display:column>
				         </c:when>
				         
				          <c:when test = "${tangible.categories[index].characteristics[indexC].type == 'IMG'}">
		          
		             
				             <display:column   title="Value"> 
				             	 <img class="img-responsive" src="${ tangible.categories[index].characteristics[indexC].value}"  width="460" height="345"> 
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

</div>

<%@ include file="../common/footer.jspf"%>