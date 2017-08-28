<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<title>Tangible Interfaces</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<nav role="navigation" class="navbar navbar-default navbar-inverse">

	<div class="">
		<a href="/" class="navbar-brand">Tangible Interfaces</a>
	</div>



</nav>

<div class="container"> 
<div class="row">

    <div class="col-sm-3 col-md-9">
      <div class="inside">
        <div class="content">
        	
        	<h2>User</h2>
        	        	
        	<div class="container">
        	<c:url var="formUrl"  value="/users/add" />
			<form:form method="post" commandName="user"  >
				
				<fieldset class="form-group">
					<form:label path="username">Username</form:label>
					<form:input path="username" type="text" class="form-control"
						required="required" />
					<form:errors path="username" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="password">Password</form:label>
					<form:label path="password">From 8 to 15 characters which contain at least one numeric digit, one uppercase, and one lowercase letter</form:label>
					<form:input path="password" type="text" class="form-control"
						required="required" onChange="CheckPassword(password)" />
					<form:errors path="password" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="firstName">First Name</form:label>
					<form:input path="firstName" type="text" class="form-control"
						required="required" />
					<form:errors path="firstName" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="lastName">Last Name</form:label>
					<form:input path="lastName" type="text" class="form-control"
						required="required" />
					<form:errors path="lastName" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="institution">Institution</form:label>
					<form:input path="institution" type="text" class="form-control"
						required="required" />
					<form:errors path="institution" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="email">Email</form:label>
					<form:input path="email" type="email" class="form-control"
						required="required" />
					<form:errors path="email" cssClass="text-warning" />
				</fieldset>
				
				<button type="submit" class="btn btn-success">Submit</button>
				<a href="/users/list" class="btn btn-info" role="button">Back</a>
			</form:form>
			</div>
        
        </div>
      </div>			     		
     </div>
</div>

</div>
</body>
</html>