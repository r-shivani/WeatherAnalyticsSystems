<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather Search</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300,400,700|" rel="stylesheet" type="text/css">
<link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="site-content">
			<div class="site-header">
				<div class="container">
				<div class="branding">
						<img src="images/logo.png" alt="" class="logo">
						<div class="logo-type">
							<h1 class="site-title">Weather App</h1>
						</div>
						<br />
			<br />
			<br />
			<div class="main-navigation">
						<button type="button" class="menu-toggle"><i class="fa fa-bars"></i></button>
						<ul class="menu">
				
						</ul> <!-- .menu -->
					</div>
					<div class="mobile-navigation"></div>

				</div>
			</div>
			<div class="hero" data-bg-image="images/banner_bg.png">
				<div class="container">
					<form action="weatherServlet" class="find-location" method="post">
						<input type="text" name="zipcode" placeholder="Enter Zipcode">
						<input type="submit" name="getweatherdata" value="Get Weather Data">
					</form>

				</div>
			</div>
			</div>
			</div>
			
	<main class="main-content">
				<div class="fullwidth-block">
					<div class="container">
						<!-- <h2 class="section-title">Live cameras</h2> -->
						 <div class="row">
						<!--	<div class="col-md-3 col-sm-6">
								<div class="live-camera">
									<figure class="live-camera-cover"><img src="images/live-camera-1.jpg" alt=""></figure>
									<h3 class="location">New York</h3>
									<small class="date">8 oct, 8:00AM</small>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="live-camera">
									<figure class="live-camera-cover"><img src="images/live-camera-2.jpg" alt=""></figure>
									<h3 class="location">Los Angeles</h3>
									<small class="date">8 oct, 8:00AM</small>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="live-camera">
									<figure class="live-camera-cover"><img src="images/live-camera-3.jpg" alt=""></figure>
									<h3 class="location">Chicago</h3>
									<small class="date">8 oct, 8:00AM</small>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="live-camera">
									<figure class="live-camera-cover"><img src="images/live-camera-4.jpg" alt=""></figure>
									<h3 class="location">London</h3>
									<small class="date">8 oct, 8:00AM</small>
								</div>
							</div>-->
						</div> 
					</div>
				</div>
				</main>
<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>
</body>
</html>