<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Krispy Kreme</title>

    <!-- Bootstrap core CSS -->
    <link href="/Krispy/lib/dependencies/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/Krispy/lib/dependencies/css/style.css" rel="stylesheet">
    
        <!-- Begin JSP Services -->
     <%@ page import="javax.servlet.http.HttpServletRequest" %>
     <%@ page import="javax.servlet.http.HttpServletResponse" %>
     <%@ page import="javax.servlet.http.HttpSession" %>
	<%@ page import="com.kk.services.*" %>
	<%@ page import="com.kk.valueObjects.*" %>
	<%@ page import="com.kk.jsp.*" %>
	<%@ page import="java.util.*" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<% HTMLServices hs = new HTMLServices(session, request, response, out);
	AdminServices as = new AdminServices(session, request, response, out);
	as.executeAdminServices();
	as.setStore(null);
    %>
   	<!-- End JSP Services -->
   	
</head>
<body>

	<!--Begin Header -->
    	<%@ include file="/WEB-INF/adminListHeader.jspf" %>
	<!--End Header -->
				<%if (as.isLoggedIn()) {
					hs.buildBackups();;
				} else { %>
					"<h3 align='center'>Please Login to View Backups</h3></br>"
				<%}%>
					
</body>
</html>