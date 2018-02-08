<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Bootstrap core CSS -->
    <link href="lib/dependencies/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="lib/dependencies/css/style.css" rel="stylesheet">
    
    <!-- Begin JSP Services -->
	<%@ page import="com.kk.services.*" %>
	<%@ page import="com.kk.valueObjects.*" %>
	<%@ page import="com.kk.jsp.*" %>
	<%@ page import="java.util.*" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<% HTMLServices hs = new HTMLServices(session, request, response, out);
	AdminServices as = new AdminServices(session, request, response, out);
	as.executeAdminServices();
    %>
   	<!-- End JSP Services -->

</head>
<body>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		
			<form role="form" action='landing.jsp' method='post'>
			<input type="hidden" name="login" value="adminLogin">
			
		<!-- User: bbeaber  Password:  -->	
				<div class="form-group"> 
					<label for="aUser">
						User Name
					</label>
					<input class="form-control" name="user" id="aUser" />
				</div>
				<div class="form-group">
					 
					<label for="aPass">
						Password
					</label>
					<input class="form-control" name="pass" id="aPass" />
				</div>
				<button type="submit" class="btn btn-default">
					Login
				</button>
			</form>
		</div>
	</div>
</div>


    <script src="lib/dependencies/jquery.min.js"></script>
    <script src="lib/dependencies/bootstrap.min.js"></script>
</body>
</html>