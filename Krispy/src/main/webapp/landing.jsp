<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="Krispy Kreme" content="Krispy Kreme Website">
    <meta name="Brandon Beaber" content="Krispy Kreme Homepage">
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon"> 
    <!--  <link rel="icon" href="images/favicon.ico" type="image/x-icon"> -->

    <title>Krispy Kreme</title>

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
	//-------------------------------------------   MUST REMOVE   ------------------------------------------//
	as.loginAdmin();
	//-------------------------------------------   MUST REMOVE   ------------------------------------------//
    %>
   	<!-- End JSP Services -->
    
    
</head>

<body>

	<!--Begin Header -->
    	<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                </button>
				<a href="landing.jsp"><img style="padding:4px 0px 0px 0px; float:none" class="navbar-brand" src="/Krispy/images/logo.png"  alt="Small Logo" onmouseover="this.src='images/logo.png';" onmouseout="this.src='images/logo.png';"  /></a>
                <!--  <a class="navbar-brand" style="float:none" href="/">Krispy Kreme</a> -->
            </div>
            
            <div  id="navbar" class="nav navbar-nav" style='float:right;'>
              <ul class="nav navbar-nav">
				
				<%if (as.isLoggedIn()) {%>
					<!--  <a href="/Krispy/Login.jsp">Logout</a> -->
					<li><form method='post' action='landing.jsp' class="collapse navbar-collapse"><input type='hidden' name='logout'>
						<button type="submit" class="collapse navbar-collapse">Logout</button>
					</form></li>
				<%} else { %>
                    <li><a href="/Krispy/Login.jsp">Login</a>  </li> 
                    <%}%>
				
				</ul>
			</div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="landing.jsp">Home</a></li>
                    
                    <li class="dropdown">
                    	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Locations
                    	<span class="carpet"></span></a>
                    	<ul class="dropdown-menu">
                    		<li><a href="/Krispy/admin/west.jsp">westO</a></li>
                    		<li><a href="/Krispy/admin/midtown.jsp">midtown</a></li>
                    		<li><a href="/Krispy/admin/lincoln.jsp">lincoln</a></li>
                    		<li><a href="/Krispy/admin/council.jsp">councilBluffs</a></li>
                    	</ul>
                    </li>
                    <li class="dropdown">
                    	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Menus
                    	<span class="carpet"></span></a>
                    	<ul class="dropdown-menu">
                    		<li><a href="/Krispy/admin/menus/westMenu.jsp">westO</a></li>
                    		<li><a href="/Krispy/admin/menus/midtownMenu.jsp">midtown</a></li>
                    		<li><a href="/Krispy/admin/menus/lincolnMenu.jsp">lincoln</a></li>
                    		<li><a href="/Krispy/admin/menus/councilMenu.jsp">councilBluffs</a></li>
                    	</ul>
                    </li>
                    <li><a href="contact.html">Contact</a></li>

                </ul>
            </div >
        </div>
    </nav>
    	<%@ include file="/WEB-INF/Slider.jspf" %>
	<!--End Header -->

	

	<div class="col-md-12">	
				<div class="panel panel-default">
				<div class="panel-body">
				<span style="font-size:20px">
			Krispy Kreme is a one of a kind Restaurant...
			</span>
				</div>
			</div>		
		</div>
		
	
    <br>

    <div class="col-md-12">
        <div class="text-center">
		<br />
            <h1>Featured Items</h1></div>
        <div class="row">
            <div class="col-md-4">
                <div class="thumbnail">
                    <img alt="Bootstrap Thumbnail First" src="images/original.jpg" />
                    <div class="caption">
                        <h3>
								Original Glaze
							</h3>
                        <p>
							Our signature melt in your mouth doughnut, made fresh every day  <br/>
                            <br />
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="thumbnail">
                    <img alt="Bootstrap Thumbnail Second" src="images/blackCoffee.jpg" />
                    <div class="caption">
                        <h3>
								Hot Coffee
							</h3>
                        <p>
                            Krispy Kreme house coffee is a one of a kind, unique blend<br />
                            <br />
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>


	<br />
	

	<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<p class="text-primary">Businsess Hours</p>
					<p>Sunday - Thursday : 5:30AM to 10PM</p>
					<p>Friday - Saturday : 5:30AM to 12AM</p>
				</div>

			</div>
		</div>
	</div>
	</div>

    <script src="lib/dependencies/jquery.min.js"></script>
    <script src="lib/dependencies/bootstrap.min.js"></script>

</body>

</html>