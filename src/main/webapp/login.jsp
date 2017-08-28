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

	<div class="navbar-collapse">
		<ul class="nav navbar-nav">
			
		</ul>
		<ul class="nav navbar-nav navbar-right">
		<li>
			<c:if test="${not empty param.login_error}">
			 <font color="#ff0000">
			 Login unsuccessful.<br/>
			 <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
			 </font>
			</c:if>

			 <form action="j_spring_security_check" method="POST"  >
			 <div class="form-group">
			 <label class="text-muted" for="username">User Name:</label>
			 <input id="username" name="j_username" type="text" />
			 <label class="text-muted" for="password">Password:</label>
			 <input id="password" name="j_password" type="password" />
			 <input type="submit" value="Log In" />
			 </div>
			 </form>
		</li> 
		<li><a href="/users/register" class="btn btn-link" role="button">Register</a></li>
		
		</ul>
	</div>

</nav>





 <div class="container">
 <div>
				<h1 lang="en" >Welcome to Tangible User Interfaces Characterization (TUIC)</h1>
				
				
				<h2 lang="en" >Tangible Interaction</h2>
				
				<p lang="en" >TANGINT / FR is a working group devoted to Tangible Interaction, supported by the AFIHM and partner of Be-greifbare Interaktion. Open to researchers, engineers and designers who wish to invest the Tangible Interaction theme through the exchange of experiences, best practices and national and international networks. See more in <a href="http://www.tangint.org/">http://www.tangint.org/ </a></p>
				
				<h2 lang="en" >Objectives</h2>
				
				<div class="row">
					<div class="col-lg-4">
						<h2 lang="en">Challenge</h2>
				
	          			<p lang="en" >The TANGINT / FR working group on Tangible Interaction, supported by the AFIHM (Francophone Association for Man-Machine Interaction), was created in 2011 by Nadine Couture and Guillaume Rivière of ESTIA. The objective of this working group is to federate the European community of Human-Machine Interaction, but also that of interaction design, around the research axis "Tangible Interaction". The challenge is that the European community appropriates this theme.  </p>
				
					</div>
					<div class="col-lg-4">
						<h2 lang="en">Openness and good practices</h2>
				
	          			<p lang="en" >The TANGINT / FR working group is open to researchers, engineers and designers who wish to mature the theme of tangible interaction by sharing their experiences, best practices and national and international networks. After the synthesis of Shaer and Hornecker in 2011, the aim of this WG is to agree on the definition of tangibility, to propose design models, to formalize evaluations and tests of use, to combine the tangible interaction with tactile interaction on interactive tables, but also to explore new avenues of research such as physical state update and tangible core interactors</p>
	          	
					
					</div>
				</div>
				</div>
</div>





</body>
</html>